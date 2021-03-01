// 页面线条
;(function (window) {
    function Dotline(option) {
        this.opt = this.extend({
            dom: 'J_dotLine',//画布id
            cw: 1100,//画布宽
            ch: 300,//画布高
            ds: 100,//点的个数
            r: 0.5,//圆点半径
            cl: '#000',//颜色
            dis: 150//触发连线的距离
        }, option);
        this.c = document.getElementById(this.opt.dom);//canvas元素id
        this.ctx = this.c.getContext('2d');
        this.c.width = this.opt.cw;//canvas宽
        this.c.height = this.opt.ch;//canvas高
        this.dotSum = this.opt.ds;//点的数量
        this.radius = this.opt.r;//圆点的半径
        this.disMax = this.opt.dis * this.opt.dis;//点与点触发连线的间距
        this.color = this.color2rgb(this.opt.cl);//设置粒子线颜色
        this.dots = [];
        //requestAnimationFrame控制canvas动画
        var RAF = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (callback) {
            window.setTimeout(callback, 1000 / 60);
        };
        var _self = this;
        //增加鼠标效果
        var mousedot = {x: null, y: null, label: 'mouse'};
        this.c.onmousemove = function (e) {
            var e = e || window.event;
            mousedot.x = e.clientX - _self.c.offsetLeft;
            mousedot.y = e.clientY - _self.c.offsetTop;
        };
        this.c.onmouseout = function (e) {
            mousedot.x = null;
            mousedot.y = null;
        }
        //控制动画
        this.animate = function () {
            _self.ctx.clearRect(0, 0, _self.c.width, _self.c.height);
            _self.drawLine([mousedot].concat(_self.dots));
            RAF(_self.animate);
        };
    }

    //合并配置项，es6直接使用obj.assign();
    Dotline.prototype.extend = function (o, e) {
        for (var key in e) {
            if (e[key]) {
                o[key] = e[key]
            }
        }
        return o;
    };
    //设置线条颜色(参考{抄袭}张鑫旭大大，http://www.zhangxinxu.com/wordpress/2010/03/javascript-hex-rgb-hsl-color-convert/)
    Dotline.prototype.color2rgb = function (colorStr) {
        var red = null,
            green = null,
            blue = null;
        var cstr = colorStr.toLowerCase();//变小写
        var cReg = /^#[0-9a-fA-F]{3,6}$/;//确定是16进制颜色码
        if (cstr && cReg.test(cstr)) {
            if (cstr.length == 4) {
                var cstrnew = '#';
                for (var i = 1; i < 4; i++) {
                    cstrnew += cstr.slice(i, i + 1).concat(cstr.slice(i, i + 1));
                }
                cstr = cstrnew;
            }
            red = parseInt('0x' + cstr.slice(1, 3));
            green = parseInt('0x' + cstr.slice(3, 5));
            blue = parseInt('0x' + cstr.slice(5, 7));
        }
        return red + ',' + green + ',' + blue;
    }
    //画点
    Dotline.prototype.addDots = function () {
        var dot;
        for (var i = 0; i < this.dotSum; i++) {//参数
            dot = {
                x: Math.floor(Math.random() * this.c.width) - this.radius,
                y: Math.floor(Math.random() * this.c.height) - this.radius,
                ax: (Math.random() * 2 - 1) / 1.5,
                ay: (Math.random() * 2 - 1) / 1.5
            }
            this.dots.push(dot);
        }
    };
    //点运动
    Dotline.prototype.move = function (dot) {
        dot.x += dot.ax;
        dot.y += dot.ay;
        //点碰到边缘返回
        dot.ax *= (dot.x > (this.c.width - this.radius) || dot.x < this.radius) ? -1 : 1;
        dot.ay *= (dot.y > (this.c.height - this.radius) || dot.y < this.radius) ? -1 : 1;
        //绘制点
        this.ctx.beginPath();
        this.ctx.arc(dot.x, dot.y, this.radius, 0, Math.PI * 2, true);
        this.ctx.stroke();
    };
    //点之间画线
    Dotline.prototype.drawLine = function (dots) {
        var nowDot;
        var _that = this;
        //自己的思路：遍历两次所有的点，比较点之间的距离，函数的触发放在animate里
        this.dots.forEach(function (dot) {

            _that.move(dot);
            for (var j = 0; j < dots.length; j++) {
                nowDot = dots[j];
                if (nowDot === dot || nowDot.x === null || nowDot.y === null) continue;//continue跳出当前循环开始新的循环
                var dx = dot.x - nowDot.x,//别的点坐标减当前点坐标
                    dy = dot.y - nowDot.y;
                var dc = dx * dx + dy * dy;
                if (Math.sqrt(dc) > Math.sqrt(_that.disMax)) continue;
                // 如果是鼠标，则让粒子向鼠标的位置移动
                if (nowDot.label && Math.sqrt(dc) > Math.sqrt(_that.disMax) / 2) {
                    dot.x -= dx * 0.02;
                    dot.y -= dy * 0.02;
                }
                var ratio;
                ratio = (_that.disMax - dc) / _that.disMax;
                _that.ctx.beginPath();
                _that.ctx.lineWidth = ratio / 2;
                _that.ctx.strokeStyle = 'rgba(' + _that.color + ',' + parseFloat(ratio + 0.2).toFixed(1) + ')';
                _that.ctx.moveTo(dot.x, dot.y);
                _that.ctx.lineTo(nowDot.x, nowDot.y);
                _that.ctx.stroke();//不描边看不出效果

                //dots.splice(dots.indexOf(dot), 1);
            }
        });
    };
    //开始动画
    Dotline.prototype.start = function () {
        var _that = this;
        this.addDots();
        setTimeout(function () {
            _that.animate();
        }, 100);
    }
    window.Dotline = Dotline;
}(window));
//线条调用
window.onload = function () {
    var dotline = new Dotline({
        dom: 'J_dotLine',//画布id
        cw: 1953,//画布宽
        ch: 1980,//画布高
        ds: 300,//点的个数
        r: 1,//圆点半径
        cl: ' #333333',//粒子线颜色
        dis: 80//触发连线的距离
    }).start();
}
(function ($) {
    $.extend($.fn, {
        divWin: function (options) {
            var opts = $.extend({
                title: 'DIV模态窗口',
                height: 400,
                width: 400, off: { left: 0, top: 0 },
                showTop: false,
                autoSize: false,
                showBorder: false

            }, options);


            var titH = 25;
            var _winBG = $("<div class='divwin-bg'></div>").appendTo($("body"));
            var _winBD = $("<div class='divwin-bd'></div>").appendTo($("body"));
            if (!opts.showBorder) {
                _winBD.css({ "border-width": "0px", "background-color": "transparent" });
            }
            if (opts.showTop) {
                _winBD.append("<div class='divwin-tit'><strong>" + opts.title + "</strong><a href='#'>关闭</a></div>");
            } else {
                titH = 0;
            }
            if (opts.autoSize) {
                opts.width = this.width() + 18;
                opts.height = this.height() + titH + 30;
            }

            var contObj = $("<div class='divwin-cont'></div>").appendTo(_winBD);
            if (opts.containTo) {
                contObj.append(opts.containTo); opts.containTo.show();
            } else {
                contObj.append(this); this.show();
            }
            var winH = $(window).height(); //alert(winH);
            var winW = $(window).width();
            if (opts.width >= winW - 20) {
                opts.width = winW - 50;
            }
            if (opts.height >= winH - 20) {
                opts.height = winH - 50;
            }

            _winBD.width(opts.width);
            contObj.height(opts.height - titH);


            var aObj = _winBD.find("div.divwin-tit").find("a");
            aObj.bind("click", function (e) {
                context.hide();
                return false;
            });

            //兼容IE6
            var ie6 = !-[1, ] && !window.XMLHttpRequest;

            this.show = function () {
                winH = $(window).height();
                winW = $(window).width();

                var srlH = window.document.body.scrollHeight;
                if (srlH < winH) {
                    srlH = winH;
                }
                var top = parseInt((winH - opts.height) / 2); //alert(window.document.body.scrollTop); alert(winH);
                var left = parseInt((winW - opts.width) / 2);
                if (ie6) {
                    top += $(window.document).scrollTop();
                    srlH += 60;
                }
                _winBG.height(srlH);
                _winBD.css({ left: (left + opts.off.left) + "px", top: (top + opts.off.top) + "px" });

                _winBG.show(); //_winBD.show();
                //alert(_winBD.html());
                if (ie6) {
                    _winBD.show();

                } else {
                    _winBD.fadeIn();
                }
            }
            this.hide = function () {
                _winBD.fadeOut(function () {
                    _winBG.hide();
                });
            }
            var context = this;
            return this;
        }
    });

    var js = document.getElementsByTagName("script");
    var jsPath = "";

    for (var i = 0; i < js.length; i++) {
        if (js[i].src.toLowerCase().indexOf("/divwin.js") != -1) {
            jsPath = js[i].src.substring(0, js[i].src.lastIndexOf("/") + 1);
            break;
        }
    }
    var headObj = document.getElementsByTagName("head")[0];
    var css = document.createElement("link");
    css.setAttribute("rel", "stylesheet");
    css.setAttribute("type", "text/css");
    css.setAttribute("href", jsPath + "divWin.css");
    headObj.appendChild(css);

})(jQuery);

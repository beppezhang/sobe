function MyDP() {
    var RLParas = {
        RL_C: null,
        MDayCount: new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31), //定义阳历中每个月的最大天数
        TheYear: new Date().getFullYear(), //定义年的变量的初始值
        TheMonth: new Date().getMonth() + 1, //定义月的变量的初始值
        TheDay: new Date().getDate(),     //定义当前日期的初始值
        CurDayObj: null,
        TxtObj: null,
       // withTime: false,
        hourC: null,
        minuteC: null,
        selType:1//1日期,2日期时间,3月份-日期
    };

    var hasInitSelectDateControl = false;

    //单击某个日期触发的事件
    var clickHandler = function (me, event) {
        if (RLParas.CurDayObj != null) {
            RLParas.CurDayObj.className = "";
            delClassName(RLParas.CurDayObj, "selday");
        }
        addClassName(me, "selday");
        RLParas.CurDayObj = me;
        if (RLParas.selType==2) {
            showEL(RLParas.hourC);//显示小时选择DIV
            initHourMinute();//初始化小时和分钟
        } else {
            var arr = RLParas.CurDayObj.id.split("_"); // alert(me.id);
            if (arr[2].length == 1) {
                arr[2] = "0" + arr[2];
            }
            if (arr[3].length == 1) {
                arr[3] = "0" + arr[3];
            }
            if (RLParas.selType == 3) {
               
                RLParas.TxtObj.value = arr[2] + "-" + arr[3];
            } else {
                RLParas.TxtObj.value = arr[1] + "-" + arr[2] + "-" + arr[3];
            }
            //alert(111);
            RLParas_hideRiLi(event);
        }
        if (onSelected) {
            onSelected(RLParas.TxtObj);
        }
        return false;
    };

    var initHourMinute = function () {
        if (RLParas.selType==2) {
            if (RLParas.TxtObj != null && RLParas.TxtObj.value != "") {
                var arr1 = RLParas.TxtObj.value.split(' ');
                var ttt = arr1[1].split(':');// alert(ttt[1]);
                var aels = RLParas.hourC.getElementsByTagName("a");
                var bb1 = 0;
                for (var a = 0; a < aels.length - 1; a++) {
                    if (aels[a].innerHTML == ttt[0]) {
                        addClassName(aels[a], "sel"); bb1++;
                    } else {
                        if (delClassName(aels[a], "sel")) {
                            bb1++;
                        }
                    }
                    if (bb1 >= 2) { break; bb1 = 0; }
                }
                aels = RLParas.minuteC.getElementsByTagName("a");
                for (var a = 0; a < aels.length - 1; a++) {
                    if (aels[a].innerHTML == ttt[1]) {
                        addClassName(aels[a], "sel"); bb1++;
                    } else {
                        if (delClassName(aels[a], "sel")) {
                            bb1++;
                        }
                    }
                    if (bb1 >= 2) { break; bb1 = 0; }
                }
            }
        }
    };

    var hideEL = function (el) {
        el.style.visibility = "hidden";
    };
    var showEL = function (el) {
        el.style.visibility = "visible";
    };
    var hourClick = function (me, event, back) {
        if (back == 1) {
            hideEL(RLParas.hourC);
        } else {
            hideEL(RLParas.hourC);
            showEL(RLParas.minuteC);
            RLParas.hour = me.innerHTML;
        }
    };

    var minuteClick = function (me, event, back) {
        if (back == 1) {
            hideEL(RLParas.minuteC);
            showEL(RLParas.hourC);
        } else {
            var arr = RLParas.CurDayObj.id.split("_"); // alert(me.id);
            RLParas.TxtObj.value = arr[1] + "-" + arr[2] + "-" + arr[3] + " " + RLParas.hour + ":" + me.innerHTML;
            hideEL(RLParas.minuteC);
            hideEL(RLParas.RL_C);
        }
    };


    function isRunYear(year) {//判断是否闰平年
        if (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }

    function getMDayCount(year, month) {  //获某月的天数,month:1-12,JS编程时索引从0开始
        var c = RLParas.MDayCount[month - 1];
        if ((month == 2) && isRunYear(year)) c++; //闰年二月为29天
        return c;
    }

    function getDOfW(year, month, day) {     //求某天的星期几
        var dt = new Date(year, month - 1, day).getDay(); return dt;
    }

    //阻止事件冒泡函数 
    function stopBubble(e) {
        if (e && e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
    }


    function showDateHtml() {
        var curDayID = "";
        var mdays = getMDayCount(RLParas.TheYear, RLParas.TheMonth);
        var fdow = getDOfW(RLParas.TheYear, RLParas.TheMonth, 1); //某月第一天是星期几？
        var tempHtml = "<ol Author='chx' id='ol_" + RLParas.TheYear + "_" + RLParas.TheMonth + "'>";
        tempHtml += getPrevMonthDaysHtml(fdow); //显示上个月在当前月视图里灰色显示的几个日期
        var d = 1;
        for (var i = fdow; i < 37; i++) {
            if (d <= mdays) {
                var li_id = RLParas.TheYear + "_" + RLParas.TheMonth + "_" + d;
                if (RLParas.TheDay == d) {
                    tempHtml += "<li><a href='javascript:void();' class='selday' onclick='return RLParas_clickHandler(this,event)' href='' id='a_" + li_id + "'>" + d + "</a></li>";
                    curDayID = "li_" + li_id;

                } else {
                    tempHtml += "<li><a href='javascript:void();' onclick='return RLParas_clickHandler(this,event)' id='a_" + li_id + "'>" + d + "</a></li>";
                }
            } else {
                tempHtml += "<li Author='chx'>&nbsp;</li>";
            }
            if ((i + 1) % 7 == 0) {//换行
                tempHtml += "</ol>";
                tempHtml += "<ol Author='chx'>";
            }
            d++;
        }
        tempHtml += getBottomBtns(); //显示除去日期显示位置以外的空余位置，可以放快捷操作按钮
        tempHtml += "</ol>";
        document.getElementById("divDays").innerHTML = tempHtml; //呈现日期
        //alert(RLParas.selType);
        initSelYear(); //初始化年份选择下拉
        initSelMonth(); //初始化月份选择
        RLParas.CurDayObj = document.getElementById(curDayID);
    }

    //初始化日期各种事件
    function initEvents() {
        var i = 0;

        var sgys = document.getElementsByName("a_sgy");
        for (i = 0; i < sgys.length; i++) {
            addEvent(sgys[i], "onclick", addMonth(-1, sgys[i]));
        }
        var xgys = document.getElementsByName("a_xgy");
        for (i = 0; i < xgys.length; i++) {
            addEvent(xgys[i], "onclick", addMonth(1, xgys[i]));
        }
        var selY = document.getElementById("selYear");
        addEvent(selY, "onchange", chgYear(selY));

        var selM = document.getElementById("selMonth");
        addEvent(selM, "onchange", chgMonth(selM));

        var syn = document.getElementsByName("a_syn")[0];
        addEvent(syn, "onclick", addYear(-1, syn));

        var xyn = document.getElementsByName("a_xyn")[0];
        addEvent(xyn, "onclick", addYear(1, xyn));

    }


    //显示除去日期显示位置以外的空余位置，可以放快捷操作按钮
    function getBottomBtns() {
        //空余5个LI<li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li>
        var hhh = "<li style='width:35px;float:right;'><a href='javascript:void();' class='reset' onclick='return RLParas_reSet(event);' Author='chx'>今天</a></li>";
        if (RLParas.selType==2 && RLParas.TxtObj.value != "") {
            hhh += "<li id='closerili' onclick='return RLParas_getTime(event);' style='width:35px;float:right;'><a>时间</a></li>";
        }
        return hhh;//"<li id='closerili' style='width:35px;float:right;'>关闭</li>";
    }

    //显示上个月在当前月视图里灰色显示的几个日期
    function getPrevMonthDaysHtml(fdow) {
        var temp = "";
        if (fdow > 0) {
            var preM = RLParas.TheMonth - 1;
            var preY = RLParas.TheYear;
            if (preM < 1) {
                preM = 12; preY--;
            }
            var preMDS = getMDayCount(preY, preM);
            for (var i = preMDS - fdow + 1; i <= preMDS; i++) {
                var li_id = preY + "_" + preM + "_" + i;
                temp += "<li class='preday' id='li_" + li_id + "'>" + i + "</li>";
            }
        }
        return temp;
    }
    //增加指定的月数
    var addMonth = function (num, me) {
        return function () {
            if (num > 12) {
                alert("增加月数不能超过12.");
            }
            RLParas.TheMonth += num;
            if (RLParas.TheMonth < 1) {
                RLParas.TheMonth = 12;
                RLParas.TheYear--;
            }
            if (RLParas.TheMonth > 12) {
                RLParas.TheMonth = 1;
                RLParas.TheYear++;
            }
            initSelMonth();
            showDateHtml();
            stopBubble(event);
        };
    };

    //增加指定的年数
    var addYear = function (num, me) {
        return function () {
            if (num > 100) {
                alert("增加年数不能超过12.");
            }
            RLParas.TheYear += num;
            if (RLParas.TheYear < 1900) {
                alert("年份不能小于1900");
                RLParas.TheYear = 1900;
            }
            if (RLParas.TheYear > 3000) {
                alert("年份不能大于3000");
                RLParas.TheYear = 3000;
            }
            initSelMonth();
            showDateHtml();
            stopBubble(event);
        };
    };

    //年份选择改变
    var chgYear = function (me) {
        return function () {
            RLParas.TheYear = parseFloat(me.value);
            showDateHtml();
        };
    };

    //月份选择改变
    var chgMonth = function (me) {
        return function () {
            RLParas.TheMonth = parseFloat(me.value);
            showDateHtml();
        };
    };

    //初始化年份选择下拉
    function initSelYear() {
        var selObj = document.getElementById("selYear");
        selObj.options.length = 0;
        for (var i = RLParas.TheYear - 10; i <= RLParas.TheYear + 10; i++) {
            var li = new Option(i.toString(), i.toString());
            selObj.options.add(li);
        }
        selObj.value = RLParas.TheYear.toString();
    }

    function initSelMonth() {
        document.getElementById("selMonth").value = RLParas.TheMonth.toString();
    }

    //为指定DOM对象增加一个类样式名
    function addClassName(obj, clsname) {
        if (obj.className == undefined || obj.className == "") {
            obj.className = clsname;
        } else {
            if (obj.className.indexOf(clsname) == -1) {
                obj.className += ' ' + clsname;
            }
        }
    }
    //为指定DOM对象删除一个类样式名
    function delClassName(obj, clsname) {
        if (obj.className == undefined || obj.className == "") {
            return false;
        } else {
            var re = false;
            if (obj.className.indexOf(clsname) != -1) {
                re = true;
            }
            if (obj.className.indexOf(' ') == -1) {
                obj.className = "";
            } else {
                var arr = obj.className.split(' ');
                var newclsname = "";
                for (var a = 0; a < arr.length; a++) {
                    if (arr[a] != clsname) {
                        newclsname += newclsname != "" ? ' ' + arr[a] : arr[a];
                    }
                }
                obj.className = newclsname;
            }

            return re;
        }
    }
    //给对象添加事件
    function addEvent(obj, eventName, fn) {
        if (obj == null) {
            return;
        }
        if (obj.addEventListener) { // Mozilla, Netscape, Firefox
            if (eventName.substr(0, 2) == "on") {
                eventName = eventName.substring(2, eventName.length);
            } // alert(eventName);
            obj.addEventListener(eventName, fn, false);
        } else { // IE
            obj.attachEvent(eventName, fn);
        }
    }


    function InitRiLiHTML() {
        var Html = "<ul class='top'>";
        Html += "<li style='width:30px;'><a class='year' href='javascript:void();' name='a_syn' title='上一年' Author='chx'>&lt;&lt;</a><a href='javascript:void();' name='a_sgy' title='上个月' Author='chx'>&lt;</a></li>";
        Html += "<li style='width:55px;' class='year'><select id='selYear' style='width:100%;' Author='chx'></select></li>";
        Html += "<li style='width:14px;' class='year'>年</li>";
        Html += "<li style='width:40px;'>";
        Html += "<select id='selMonth' style='width:100%;' Author='chx'>";
        Html += "<option value='1'>01</option>";
        Html += "<option value='2'>02</option>";
        Html += "<option value='3'>03</option>";
        Html += "<option value='4'>04</option>";
        Html += "<option value='5'>05</option>";
        Html += "<option value='6'>06</option>";
        Html += "<option value='7'>07</option>";
        Html += "<option value='8'>08</option>";
        Html += "<option value='9'>09</option>";
        Html += "<option value='10'>10</option>";
        Html += "<option value='11'>11</option>";
        Html += "<option value='12'>12</option>";
        Html += "</select></li>";
        Html += "<li style='width:14px;'>月</li>";
        Html += "<li style='width:30px;float:right;'><a href='javascript:void();'  name='a_xgy' title='下个月' Author='chx'>&gt;</a>";
        Html += "<a class='year' href='javascript:void();'  name='a_xyn' title='下一年' Author='chx'>&gt;&gt;</a>";
        Html += "</li></ul>";

        Html += "<ul class='week' Author='chx'><li Author='chx'>日</li><li Author='chx'>一</li><li Author='chx'>二</li><li Author='chx'>三</li><li Author='chx'>四</li><li Author='chx'>五</li><li Author='chx'>六</li></ul>";
        Html += "<div id='divDays' class='divdays'></div>";
        if (RLParas.selType==2) {
            Html += "<div class='hours' Author='chx'>";
            for (var i = 1; i <= 24; i++) {
                var istr = i > 9 ? i : "0" + i;
                Html += "<a href='javascript:void();' onclick='return RLParas_hourClick(this,event);'>" + istr + "</a>";
            }
            Html += "<a href='javascript:void();'  onclick='return RLParas_hourClick(this,event,1);' class='back'>&lt;&lt;</a>";
            Html += "</div>";

            Html += "<div class='minutes' Author='chx'>";
            for (var i = 0; i <= 59; i++) {
                var istr = i > 9 ? i : "0" + i;
                Html += "<a href='javascript:void();'  onclick='return RLParas_minuteClick(this,event);'>" + istr + "</a>";
            }
            Html += "<a href='javascript:void();'  onclick='return RLParas_minuteClick(this,event,1);' class='back'>&lt;&lt;</a>";
            Html += "</div>";
        }

        RLParas.RL_C = document.createElement("div");
        RLParas.RL_C.setAttribute("Author", "chx");
        addClassName(RLParas.RL_C, "divMyRiLi");
        RLParas.RL_C.innerHTML = Html;

        var body = document.getElementsByTagName("body");
        body[0].appendChild(RLParas.RL_C);
    }

    var domPos = function () {
        var _X = 0; var _Y = 0;
        this.getX = function () { return _X; };
        this.setX = function (x) { _X = x; };
        this.getY = function () { return _Y; };
        this.setY = function (y) { _Y = y; };
        var _W = 0; var _H = 0;
        this.getW = function () { return _W; };
        this.setW = function (w) { _W = w; };
        this.getH = function () { return _H; };
        this.setH = function (h) { _H = h; };
    };

    function positionOffset(element) {
        var top = 0, left = 0;
        do {
            top += element.offsetTop || 0;
            left += element.offsetLeft || 0;
            element = element.offsetParent;
        } while (element);
        return { top: top, left: left };
    }

    function getDomPos(domObj) {
        var pos = new domPos();
        var off = positionOffset(domObj);
        pos.setX(off.left);
        pos.setY(off.top);
        pos.setH(domObj.offsetHeight);
        pos.setW(domObj.offsetWidth);
        return pos;
    }
    var onSelected = null;
    this.setday = function (me, fn) {
        RLParas.TxtObj = me;
        if (onSelected == undefined && fn != undefined && typeof (fn)=="function") {
            onSelected = fn;
        }
        RLParas.selType = 1;
        if (me.getAttribute("seltype")!=undefined) {
            if (me.getAttribute("seltype") == "datetime") {
                RLParas.selType = 2;
            } else if (me.getAttribute("seltype") == "monthdate") {
                RLParas.selType = 3;
            }
        } else {
            var ckStr = me.getAttribute("ck");
            if (ckStr != undefined && ckStr != "") {
                var obj = eval("(" + ckStr + ")");
                if (obj.type == "datetime") {
                    RLParas.selType = 2;
                } else if (obj.type == "monthdate") {
                    RLParas.selType = 3;
                }
            }
        }

        //alert(withTime);
       

        if (!hasInitSelectDateControl) {
            InitRiLiHTML();
            initEvents();
            addEvent(document, "onclick", RLParas_hideRiLi);

            var divs = RLParas.RL_C.getElementsByTagName("div");
            RLParas.hourC = divs[1];
            RLParas.minuteC = divs[2];

            hasInitSelectDateControl = true;

            
        }
        if (RLParas.selType == 3) {
            $("div.divMyRiLi").find(".year").hide();
        } else {
            $("div.divMyRiLi").find(".year").show();
        }
        addClassName(me, "seldate");
        if (me.value != "") {
            var arr = me.value.split('-');
            if (parseFloat(arr[0]) > 1900) {
                RLParas.TheYear = parseFloat(arr[0]);
                RLParas.TheMonth = parseFloat(arr[1]);
                RLParas.TheDay = parseFloat(arr[2]);
            }
        }
        showDateHtml();

        var pos = getDomPos(me);
        RLParas.RL_C.style.top = pos.getY() + pos.getH() - 2 + "px";
        RLParas.RL_C.style.left = pos.getX() - 2 + "px";
        RLParas.RL_C.style.visibility = "visible";
    };

    window.RLParas_hideRiLi = function (event) {
        if (event.target) {
            with (event.target) {
                if (tagName != "INPUT" && tagName != "OPTION" && getAttribute("Author") == null) {
                    if (RLParas.hourC) { hideEL(RLParas.hourC); }
                    if (RLParas.minuteC) { hideEL(RLParas.minuteC); }
                    hideEL(RLParas.RL_C);
                }
            }
        } else {
            with (window.event.srcElement) {
               
                
                if (tagName != "INPUT" && tagName != "OPTION" && getAttribute("Author") == null) {
                    if (RLParas.hourC) { hideEL(RLParas.hourC); }
                    if (RLParas.minuteC) { hideEL(RLParas.minuteC); }
                    hideEL(RLParas.RL_C);
                }
            }
        }

    };
    //单击某个日期触发的事件
    window.RLParas_clickHandler = function (me, event) {
        clickHandler(me, event);
        stopBubble(event);
        return false;
    };

    window.RLParas_hourClick = function (me, event, back) {
        hourClick(me, event, back);
        stopBubble(event);
        return false;
    };

    window.RLParas_minuteClick = function (me, event, back) {
        minuteClick(me, event, back);
        stopBubble(event);
        return false;
    };

    window.RLParas_reSet = function (event) {
        RLParas.TheYear = new Date().getFullYear();
        RLParas.TheMonth = new Date().getMonth() + 1;
        RLParas.TheDay = new Date().getDate();
        showDateHtml();

        stopBubble(event);
        return false;
    };

    window.RLParas_getTime = function (event) {
        showEL(RLParas.hourC);
        hideEL(RLParas.minuteC);
        initHourMinute();
        stopBubble(event);
        return false;
    };

    var js = document.getElementsByTagName("script"); //alert();
    var jsPath = "";
    for (var i = 0; i < js.length; i++) {
        if (js[i].src.toLowerCase().indexOf("datesel/seldate.js") != -1) {
            jsPath = js[i].src.substring(0, js[i].src.lastIndexOf("/") + 1);
            break;
        }
    }
    var headObj = document.getElementsByTagName("head")[0];
    var css = document.createElement("link");
    css.setAttribute("rel", "stylesheet");
    css.setAttribute("type", "text/css");
    css.setAttribute("href", jsPath + "/theme/default/seldate.css");
    headObj.appendChild(css);
}

var mydp_chx = new MyDP();
window.setday = function (me, fn) {
    mydp_chx.setday(me, fn);
};

MyDP.prototype.onSelected = function () {
    //alert("onSelected");
};


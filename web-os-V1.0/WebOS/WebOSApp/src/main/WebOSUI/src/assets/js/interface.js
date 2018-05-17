/*
 * Interface elements for jQuery - http://interface.eyecon.ro
 *
 * Copyright (c) 2006 Stefan Petre
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 */
jQuery.iUtil = {
  getPosition: function (e) {
    var x = 0;
    var y = 0;
    var restoreStyle = false;
    var es = e.style;
    var oldVisibility;
    var oldPosition
    if (jQuery(e).css('display') == 'none') {
      oldVisibility = es.visibility;
      oldPosition = es.position;
      es.visibility = 'hidden';
      es.display = 'block';
      es.position = 'absolute';
      restoreStyle = true
    }
    var el = e;
    while (el) {
      x += el.offsetLeft + (el.currentStyle && !jQuery.browser.opera ? parseInt(el.currentStyle.borderLeftWidth) || 0 : 0);
      y += el.offsetTop + (el.currentStyle && !jQuery.browser.opera ? parseInt(el.currentStyle.borderTopWidth) || 0 : 0);
      el = el.offsetParent
    }
    el = e;
    while (el && el.tagName && el.tagName.toLowerCase() != 'body') {
      x -= el.scrollLeft || 0;
      y -= el.scrollTop || 0;
      el = el.parentNode
    }
    if (restoreStyle) {
      es.display = 'none';
      es.position = oldPosition;
      es.visibility = oldVisibility
    }
    return {
      x: x,
      y: y
    }
  },
  getPositionLite: function (el) {
    var x = 0,
      y = 0;
    while (el) {
      x += el.offsetLeft || 0;
      y += el.offsetTop || 0;
      el = el.offsetParent
    }
    return {
      x: x,
      y: y
    }
  },
  getSize: function (e) {
    var w = jQuery.css(e, 'width');
    var h = jQuery.css(e, 'height');
    var wb = 0;
    var hb = 0;
    var es = e.style;
    var oldVisibility;
    var oldPosition;
    if (jQuery(e).css('display') != 'none') {
      wb = e.offsetWidth;
      hb = e.offsetHeight
    } else {
      oldVisibility = es.visibility;
      oldPosition = es.position;
      es.visibility = 'hidden';
      es.display = 'block';
      es.position = 'absolute';
      wb = e.offsetWidth;
      hb = e.offsetHeight;
      es.display = 'none';
      es.position = oldPosition;
      es.visibility = oldVisibility
    }
    return {
      w: w,
      h: h,
      wb: wb,
      hb: hb
    }
  },
  getSizeLite: function (el) {
    return {
      wb: el.offsetWidth || 0,
      hb: el.offsetHeight || 0
    }
  },
  getClient: function (e) {
    var h,
      w,
      de;
    if (e) {
      w = e.clientWidth;
      h = e.clientHeight
    } else {
      de = document.documentElement;
      w = window.innerWidth || self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
      h = window.innerHeight || self.innerHeight || (de && de.clientHeight) || document.body.clientHeight
    }
    return {
      w: w,
      h: h
    }
  },
  getScroll: function (e) {
    var t,
      l,
      w,
      h,
      iw,
      ih;
    if (e && e.nodeName.toLowerCase() != 'body') {
      t = e.scrollTop;
      l = e.scrollLeft;
      w = e.scrollWidth;
      h = e.scrollHeight;
      iw = 0;
      ih = 0
    } else {
      if (document.documentElement && document.documentElement.scrollTop) {
        t = document.documentElement.scrollTop;
        l = document.documentElement.scrollLeft;
        w = document.documentElement.scrollWidth;
        h = document.documentElement.scrollHeight
      } else if (document.body) {
        t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight
      }
      iw = self.innerWidth || document.documentElement.clientWidth || document.body.clientWidth || 0;
      ih = self.innerHeight || document.documentElement.clientHeight || document.body.clientHeight || 0
    }
    return {
      t: t,
      l: l,
      w: w,
      h: h,
      iw: iw,
      ih: ih
    }
  },
  getMargins: function (e, toInteger) {
    var el = jQuery(e);
    var t = el.css('marginTop') || '';
    var r = el.css('marginRight') || '';
    var b = el.css('marginBottom') || '';
    var l = el.css('marginLeft') || '';
    if (toInteger) return {
      t: parseInt(t) || 0,
      r: parseInt(r) || 0,
      b: parseInt(b) || 0,
      l: parseInt(l)
    };
    else return {
      t: t,
      r: r,
      b: b,
      l: l
    }
  },
  getPadding: function (e, toInteger) {
    var el = jQuery(e);
    var t = el.css('paddingTop') || '';
    var r = el.css('paddingRight') || '';
    var b = el.css('paddingBottom') || '';
    var l = el.css('paddingLeft') || '';
    if (toInteger) return {
      t: parseInt(t) || 0,
      r: parseInt(r) || 0,
      b: parseInt(b) || 0,
      l: parseInt(l)
    };
    else return {
      t: t,
      r: r,
      b: b,
      l: l
    }
  },
  getBorder: function (e, toInteger) {
    var el = jQuery(e);
    var t = el.css('borderTopWidth') || '';
    var r = el.css('borderRightWidth') || '';
    var b = el.css('borderBottomWidth') || '';
    var l = el.css('borderLeftWidth') || '';
    if (toInteger) return {
      t: parseInt(t) || 0,
      r: parseInt(r) || 0,
      b: parseInt(b) || 0,
      l: parseInt(l) || 0
    };
    else return {
      t: t,
      r: r,
      b: b,
      l: l
    }
  },
  getPointer: function (event) {
    var x = event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft)) || 0;
    var y = event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop)) || 0;
    return {
      x: x,
      y: y
    }
  },
  traverseDOM: function (nodeEl, func) {
    func(nodeEl);
    nodeEl = nodeEl.firstChild;
    while (nodeEl) {
      jQuery.iUtil.traverseDOM(nodeEl, func);
      nodeEl = nodeEl.nextSibling
    }
  },
  purgeEvents: function (nodeEl) {
    jQuery.iUtil.traverseDOM(nodeEl,
      function (el) {
        for (var attr in el) {
          if (typeof el[attr] === 'function') {
            el[attr] = null
          }
        }
      })
  },
  centerEl: function (el, axis) {
    var clientScroll = $.iUtil.getScroll();
    var windowSize = $.iUtil.getSize(el);
    if (!axis || axis == 'vertically') $(el).css({
      top: clientScroll.t + ((Math.max(clientScroll.h, clientScroll.ih) - clientScroll.t - windowSize.hb) / 2) + 'px'
    });
    if (!axis || axis == 'horizontally') $(el).css({
      left: clientScroll.l + ((Math.max(clientScroll.w, clientScroll.iw) - clientScroll.l - windowSize.wb) / 2) + 'px'
    })
  },
  fixPNG: function (el, emptyGIF) {
    var images = $('img[@src*="png"]', el || document),
      png;
    images.each(function () {
      png = this.src;
      this.src = emptyGIF;
      this.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + png + "')"
    })
  }
};
[].indexOf || (Array.prototype.indexOf = function (v, n) {
  n = (n == null) ? 0 : n;
  var m = this.length;
  for (var i = n; i < m; i++) if (this[i] == v) return i;
  return -1
});
jQuery.fxCheckTag = function (e) {
  if (/^tr$|^td$|^tbody$|^caption$|^thead$|^tfoot$|^col$|^colgroup$|^th$|^body$|^header$|^script$|^frame$|^frameset$|^option$|^optgroup$|^meta$/i.test(e.nodeName)) return false;
  else return true
};
jQuery.fx.destroyWrapper = function (e, old) {
  var c = e.firstChild;
  var cs = c.style;
  cs.position = old.position;
  cs.marginTop = old.margins.t;
  cs.marginLeft = old.margins.l;
  cs.marginBottom = old.margins.b;
  cs.marginRight = old.margins.r;
  cs.top = old.top + 'px';
  cs.left = old.left + 'px';
  e.parentNode.insertBefore(c, e);
  e.parentNode.removeChild(e)
};
jQuery.fx.buildWrapper = function (e) {
  if (!jQuery.fxCheckTag(e)) return false;
  var t = jQuery(e);
  var es = e.style;
  var restoreStyle = false;
  var oldStyle = {};
  var oldVisibility ;
  oldStyle.position = t.css('position');
  if (t.css('display') == 'none') {
    oldVisibility = t.css('visibility');
    es.visibility = 'hidden';
    es.display = '';
    restoreStyle = true
  }
  oldStyle.sizes = jQuery.iUtil.getSize(e);
  oldStyle.margins = jQuery.iUtil.getMargins(e);
  var oldFloat = e.currentStyle ? e.currentStyle.styleFloat : t.css('float');
  oldStyle.top = parseInt(t.css('top')) || 0;
  oldStyle.left = parseInt(t.css('left')) || 0;
  var wid = 'w_' + parseInt(Math.random() * 10000);
  var wr = document.createElement(/^img$|^br$|^input$|^hr$|^select$|^textarea$|^object$|^iframe$|^button$|^form$|^table$|^ul$|^dl$|^ol$/i.test(e.nodeName) ? 'div' : e.nodeName);
  jQuery.attr(wr, 'id', wid);
  wr.className = 'fxWrapper';
  var wrs = wr.style;
  var top = 0;
  var left = 0;
  if (oldStyle.position == 'relative' || oldStyle.position == 'absolute') {
    top = oldStyle.top;
    left = oldStyle.left
  }
  wrs.display = 'none';
  wrs.top = top + 'px';
  wrs.left = left + 'px';
  wrs.position = oldStyle.position != 'relative' && oldStyle.position != 'absolute' ? 'relative' : oldStyle.position;
  wrs.overflow = 'hidden';
  wrs.height = oldStyle.sizes.hb + 'px';
  wrs.width = oldStyle.sizes.wb + 'px';
  wrs.marginTop = oldStyle.margins.t;
  wrs.marginRight = oldStyle.margins.r;
  wrs.marginBottom = oldStyle.margins.b;
  wrs.marginLeft = oldStyle.margins.l;
  if (jQuery.browser.msie) {
    wrs.styleFloat = oldFloat
  } else {
    wrs.cssFloat = oldFloat
  }
  e.parentNode.insertBefore(wr, e);
  es.marginTop = '0px';
  es.marginRight = '0px';
  es.marginBottom = '0px';
  es.marginLeft = '0px';
  es.position = 'absolute';
  es.listStyle = 'none';
  es.top = '0px';
  es.left = '0px';
  if (restoreStyle) {
    es.display = 'none';
    es.visibility = oldVisibility
  }
  wr.appendChild(e);
  wrs.display = 'block';
  return {
    oldStyle: oldStyle,
    wrapper: jQuery(wr)
  }
};
jQuery.fx.namedColors = {
  aqua: [0, 255, 255],
  azure: [240, 255, 255],
  beige: [245, 245, 220],
  black: [0, 0, 0],
  blue: [0, 0, 255],
  brown: [165, 42, 42],
  cyan: [0, 255, 255],
  darkblue: [0, 0, 139],
  darkcyan: [0, 139, 139],
  darkgrey: [169, 169, 169],
  darkgreen: [0, 100, 0],
  darkkhaki: [189, 183, 107],
  darkmagenta: [139, 0, 139],
  darkolivegreen: [85, 107, 47],
  darkorange: [255, 140, 0],
  darkorchid: [153, 50, 204],
  darkred: [139, 0, 0],
  darksalmon: [233, 150, 122],
  darkviolet: [148, 0, 211],
  fuchsia: [255, 0, 255],
  gold: [255, 215, 0],
  green: [0, 128, 0],
  indigo: [75, 0, 130],
  khaki: [240, 230, 140],
  lightblue: [173, 216, 230],
  lightcyan: [224, 255, 255],
  lightgreen: [144, 238, 144],
  lightgrey: [211, 211, 211],
  lightpink: [255, 182, 193],
  lightyellow: [255, 255, 224],
  lime: [0, 255, 0],
  magenta: [255, 0, 255],
  maroon: [128, 0, 0],
  navy: [0, 0, 128],
  olive: [128, 128, 0],
  orange: [255, 165, 0],
  pink: [255, 192, 203],
  purple: [128, 0, 128],
  red: [255, 0, 0],
  silver: [192, 192, 192],
  white: [255, 255, 255],
  yellow: [255, 255, 0]
};
jQuery.fx.parseColor = function (color, notColor) {
  if (jQuery.fx.namedColors[color]) return {
    r: jQuery.fx.namedColors[color][0],
    g: jQuery.fx.namedColors[color][1],
    b: jQuery.fx.namedColors[color][2]
  };
  else if (result = /^rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)$/.exec(color)) return {
    r: parseInt(result[1]),
    g: parseInt(result[2]),
    b: parseInt(result[3])
  };
  else if (result = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)$/.exec(color)) return {
    r: parseFloat(result[1]) * 2.55,
    g: parseFloat(result[2]) * 2.55,
    b: parseFloat(result[3]) * 2.55
  };
  else if (result = /^#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])$/.exec(color)) return {
    r: parseInt("0x" + result[1] + result[1]),
    g: parseInt("0x" + result[2] + result[2]),
    b: parseInt("0x" + result[3] + result[3])
  };
  else if (result = /^#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})$/.exec(color)) return {
    r: parseInt("0x" + result[1]),
    g: parseInt("0x" + result[2]),
    b: parseInt("0x" + result[3])
  };
  else return notColor == true ? false : {
        r: 255,
        g: 255,
        b: 255
      }
};
jQuery.fx.cssProps = {
  borderBottomWidth: 1,
  borderLeftWidth: 1,
  borderRightWidth: 1,
  borderTopWidth: 1,
  bottom: 1,
  fontSize: 1,
  height: 1,
  left: 1,
  letterSpacing: 1,
  lineHeight: 1,
  marginBottom: 1,
  marginLeft: 1,
  marginRight: 1,
  marginTop: 1,
  maxHeight: 1,
  maxWidth: 1,
  minHeight: 1,
  minWidth: 1,
  opacity: 1,
  outlineOffset: 1,
  outlineWidth: 1,
  paddingBottom: 1,
  paddingLeft: 1,
  paddingRight: 1,
  paddingTop: 1,
  right: 1,
  textIndent: 1,
  top: 1,
  width: 1,
  zIndex: 1
};
jQuery.fx.colorCssProps = {
  backgroundColor: 1,
  borderBottomColor: 1,
  borderLeftColor: 1,
  borderRightColor: 1,
  borderTopColor: 1,
  color: 1,
  outlineColor: 1
};
jQuery.fx.cssSides = ['Top', 'Right', 'Bottom', 'Left'];
jQuery.fx.cssSidesEnd = {
  'borderWidth': ['border', 'Width'],
  'borderColor': ['border', 'Color'],
  'margin': ['margin', ''],
  'padding': ['padding', '']
};
jQuery.fn.extend({
  animate: function (prop, speed, easing, callback) {
    return this.queue(function () {
      var opt = jQuery.speed(speed, easing, callback);
      var e = new jQuery.fxe(this, opt, prop)
    })
  },
  pause: function (speed, callback) {
    return this.queue(function () {
      var opt = jQuery.speed(speed, callback);
      var e = new jQuery.pause(this, opt)
    })
  },
  stop: function (step) {
    return this.each(function () {
      if (this.animationHandler) jQuery.stopAnim(this, step)
    })
  },
  stopAll: function (step) {
    return this.each(function () {
      if (this.animationHandler) jQuery.stopAnim(this, step);
      if (this.queue && this.queue['fx']) this.queue.fx = []
    })
  }
});
jQuery.extend({
  pause: function (elem, options) {
    var z = this,
      values;
    z.step = function () {
      if (jQuery.isFunction(options.complete)) options.complete.apply(elem)
    };
    z.timer = setInterval(function () {
        z.step()
      },
      options.duration);
    elem.animationHandler = z
  },
  easing: {
    linear: function (p, n, firstNum, delta, duration) {
      return (( -Math.cos(p * Math.PI) / 2) + 0.5) * delta + firstNum
    }
  },
  fxe: function (elem, options, prop) {
    var z = this,
      values;
    var y = elem.style;
    var oldOverflow = jQuery.css(elem, "overflow");
    var oldDisplay = jQuery.css(elem, "display");
    var props = {};
    z.startTime = (new Date()).getTime();
    options.easing = options.easing && jQuery.easing[options.easing] ? options.easing : 'linear';
    z.getValues = function (tp, vp) {
      if (jQuery.fx.cssProps[tp]) {
        if (vp == 'show' || vp == 'hide' || vp == 'toggle') {
          if (!elem.orig) elem.orig = {};
          var r = parseFloat(jQuery.curCSS(elem, tp));
          elem.orig[tp] = r && r > -10000 ? r : (parseFloat(jQuery.css(elem, tp)) || 0);
          vp = vp == 'toggle' ? (oldDisplay == 'none' ? 'show' : 'hide') : vp;
          options[vp] = true;
          props[tp] = vp == 'show' ? [0, elem.orig[tp]] : [elem.orig[tp], 0];
          if (tp != 'opacity') y[tp] = props[tp][0] + (tp != 'zIndex' && tp != 'fontWeight' ? 'px' : '');
          else jQuery.attr(y, "opacity", props[tp][0])
        } else {
          props[tp] = [parseFloat(jQuery.curCSS(elem, tp)), parseFloat(vp) || 0]
        }
      } else if (jQuery.fx.colorCssProps[tp]) props[tp] = [jQuery.fx.parseColor(jQuery.curCSS(elem, tp)), jQuery.fx.parseColor(vp)];
      else if (/^margin$|padding$|border$|borderColor$|borderWidth$/i.test(tp)) {
        var m = vp.replace(/\s+/g, ' ').replace(/rgb\s*\(\s*/g, 'rgb(').replace(/\s*,\s*/g, ',').replace(/\s*\)/g, ')').match(/([^\s]+)/g);
        switch (tp) {
          case 'margin':
          case 'padding':
          case 'borderWidth':
          case 'borderColor':
            m[3] = m[3] || m[1] || m[0];
            m[2] = m[2] || m[0];
            m[1] = m[1] || m[0];
            for (var i = 0; i < jQuery.fx.cssSides.length; i++) {
              var nmp = jQuery.fx.cssSidesEnd[tp][0] + jQuery.fx.cssSides[i] + jQuery.fx.cssSidesEnd[tp][1];
              props[nmp] = tp == 'borderColor' ? [jQuery.fx.parseColor(jQuery.curCSS(elem, nmp)), jQuery.fx.parseColor(m[i])] : [parseFloat(jQuery.curCSS(elem, nmp)), parseFloat(m[i])]
            }
            break;
          case 'border':
            for (var i = 0; i < m.length; i++) {
              var floatVal = parseFloat(m[i]);
              var sideEnd = !isNaN(floatVal) ? 'Width' : (!/transparent|none|hidden|dotted|dashed|solid|double|groove|ridge|inset|outset/i.test(m[i]) ? 'Color' : false);
              if (sideEnd) {
                for (var j = 0; j < jQuery.fx.cssSides.length; j++) {
                  nmp = 'border' + jQuery.fx.cssSides[j] + sideEnd;
                  props[nmp] = sideEnd == 'Color' ? [jQuery.fx.parseColor(jQuery.curCSS(elem, nmp)), jQuery.fx.parseColor(m[i])] : [parseFloat(jQuery.curCSS(elem, nmp)), floatVal]
                }
              } else {
                y['borderStyle'] = m[i]
              }
            }
            break
        }
      } else {
        y[tp] = vp
      }
      return false
    };
    for (p in prop) {
      if (p == 'style') {
        var newStyles = jQuery.parseStyle(prop[p]);
        for (np in newStyles) {
          this.getValues(np, newStyles[np])
        }
      } else if (p == 'className') {
        if (document.styleSheets) for (var i = 0; i < document.styleSheets.length; i++) {
          var cssRules = document.styleSheets[i].cssRules || document.styleSheets[i].rules || null;
          if (cssRules) {
            for (var j = 0; j < cssRules.length; j++) {
              if (cssRules[j].selectorText == '.' + prop[p]) {
                var rule = new RegExp('\.' + prop[p] + ' {');
                var styles = cssRules[j].style.cssText;
                var newStyles = jQuery.parseStyle(styles.replace(rule, '').replace(/}/g, ''));
                for (np in newStyles) {
                  this.getValues(np, newStyles[np])
                }
              }
            }
          }
        }
      } else {
        this.getValues(p, prop[p])
      }
    }
    y.display = oldDisplay == 'none' ? 'block' : oldDisplay;
    y.overflow = 'hidden';
    z.step = function () {
      var t = (new Date()).getTime();
      if (t > options.duration + z.startTime) {
        clearInterval(z.timer);
        z.timer = null;
        for (p in props) {
          if (p == "opacity") jQuery.attr(y, "opacity", props[p][1]);
          else if (typeof props[p][1] == 'object') y[p] = 'rgb(' + props[p][1].r + ',' + props[p][1].g + ',' + props[p][1].b + ')';
          else y[p] = props[p][1] + (p != 'zIndex' && p != 'fontWeight' ? 'px' : '')
        }
        if (options.hide || options.show) for (var p in elem.orig) if (p == "opacity") jQuery.attr(y, p, elem.orig[p]);
        else y[p] = "";
        y.display = options.hide ? 'none' : (oldDisplay != 'none' ? oldDisplay : 'block');
        y.overflow = oldOverflow;
        elem.animationHandler = null;
        if (jQuery.isFunction(options.complete)) options.complete.apply(elem)
      } else {
        var n = t - this.startTime;
        var pr = n / options.duration;
        for (p in props) {
          if (typeof props[p][1] == 'object') {
            y[p] = 'rgb(' + parseInt(jQuery.easing[options.easing](pr, n, props[p][0].r, (props[p][1].r - props[p][0].r), options.duration)) + ',' + parseInt(jQuery.easing[options.easing](pr, n, props[p][0].g, (props[p][1].g - props[p][0].g), options.duration)) + ',' + parseInt(jQuery.easing[options.easing](pr, n, props[p][0].b, (props[p][1].b - props[p][0].b), options.duration)) + ')'
          } else {
            var pValue = jQuery.easing[options.easing](pr, n, props[p][0], (props[p][1] - props[p][0]), options.duration);
            if (p == "opacity") jQuery.attr(y, "opacity", pValue);
            else y[p] = pValue + (p != 'zIndex' && p != 'fontWeight' ? 'px' : '')
          }
        }
      }
    };
    z.timer = setInterval(function () {
        z.step()
      },
      13);
    elem.animationHandler = z
  },
  stopAnim: function (elem, step) {
    if (step) elem.animationHandler.startTime -= 100000000;
    else {
      window.clearInterval(elem.animationHandler.timer);
      elem.animationHandler = null;
      jQuery.dequeue(elem, "fx")
    }
  }
});
jQuery.parseStyle = function (styles) {
  var newStyles = {};
  if (typeof styles == 'string') {
    styles = styles.toLowerCase().split(';');
    for (var i = 0; i < styles.length; i++) {
      rule = styles[i].split(':');
      if (rule.length == 2) {
        newStyles[jQuery.trim(rule[0].replace(/\-(\w)/g,
          function (m, c) {
            return c.toUpperCase()
          }))] = jQuery.trim(rule[1])
      }
    }
  }
  return newStyles
};
jQuery.iFisheye = {
  build: function (options) {
    return this.each(function () {
      var el = this;
      el.fisheyeCfg = {
        items: jQuery(options.items, this),
        container: jQuery(options.container, this),
        pos: jQuery.iUtil.getPosition(this),
        itemWidth: options.itemWidth,
        itemsText: options.itemsText,
        proximity: options.proximity,
        valign: options.valign,
        halign: options.halign,
        maxWidth: options.maxWidth
      };
      jQuery.iFisheye.positionContainer(el, 0);
      jQuery(window).bind('resize',
        function () {
          el.fisheyeCfg.pos = jQuery.iUtil.getPosition(el);
          jQuery.iFisheye.positionContainer(el, 0);
          jQuery.iFisheye.positionItems(el)
        });
      jQuery.iFisheye.positionItems(el);
      el.fisheyeCfg.items.bind('mouseover',
        function () {
          jQuery(el.fisheyeCfg.itemsText, this).get(0).style.display = 'block'
        }).bind('mouseout',
        function () {
          jQuery(el.fisheyeCfg.itemsText, this).get(0).style.display = 'none'
        });
      jQuery(document).bind('mousemove',
        function (e) {
          var pointer = jQuery.iUtil.getPointer(e);
          var toAdd = 0;
          if (el.fisheyeCfg.halign && el.fisheyeCfg.halign == 'center') var posx = pointer.x - el.fisheyeCfg.pos.x - (el.offsetWidth - el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.length) / 2 - el.fisheyeCfg.itemWidth / 2;
          else if (el.fisheyeCfg.halign && el.fisheyeCfg.halign == 'right') var posx = pointer.x - el.fisheyeCfg.pos.x - el.offsetWidth + el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.length;
          else var posx = pointer.x - el.fisheyeCfg.pos.x;
          var posy = Math.pow(pointer.y - el.fisheyeCfg.pos.y - el.offsetHeight / 2, 2);
          el.fisheyeCfg.items.each(function (nr) {
            var distance = Math.sqrt(Math.pow(posx - nr * el.fisheyeCfg.itemWidth, 2) + posy);
            distance -= el.fisheyeCfg.itemWidth / 2;
            distance = distance < 0 ? 0 : distance;
            distance = distance > el.fisheyeCfg.proximity ? el.fisheyeCfg.proximity : distance;
            distance = el.fisheyeCfg.proximity - distance;
            var extraWidth = el.fisheyeCfg.maxWidth * distance / el.fisheyeCfg.proximity;
            this.style.width = el.fisheyeCfg.itemWidth + extraWidth + 'px';
            this.style.left = el.fisheyeCfg.itemWidth * nr + toAdd + 'px';
            toAdd += extraWidth
          });
          jQuery.iFisheye.positionContainer(el, toAdd)
        })
    })
  },
  positionContainer: function (el, toAdd) {
    if (el.fisheyeCfg.halign) if (el.fisheyeCfg.halign == 'center') el.fisheyeCfg.container.get(0).style.left = (el.offsetWidth - el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.length) / 2 - toAdd / 2 + 'px';
    else if (el.fisheyeCfg.halign == 'left') el.fisheyeCfg.container.get(0).style.left = -toAdd / el.fisheyeCfg.items.length + 'px';
    else if (el.fisheyeCfg.halign == 'right') el.fisheyeCfg.container.get(0).style.left = (el.offsetWidth - el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.length) - toAdd / 2 + 'px';
    el.fisheyeCfg.container.get(0).style.width = el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.length + toAdd + 'px'
  },
  positionItems: function (el) {
    el.fisheyeCfg.items.each(function (nr) {
      this.style.width = el.fisheyeCfg.itemWidth + 'px';
      this.style.left = el.fisheyeCfg.itemWidth * nr + 'px'
    })
  }
};
jQuery.fn.Fisheye = jQuery.iFisheye.build;

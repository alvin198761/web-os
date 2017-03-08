<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>远程操作</title>

  <link rel="stylesheet" type="text/css" href="guacamole/guacamole.css"/>
</head>
<body>
<div id="display"></div>

<script src="guacamole/guacamole-all.min.js"></script>
<!-- Init -->
<script type="text/javascript"> /* <![CDATA[ */

var url = location.href;
var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
var paraObj = {};
var i, j;
for (i = 0; j = paraString[i]; i++) {
  paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
}
var id = decodeURI(paraObj['id']);
var type = decodeURI(paraObj['type']);
console.log(id, type)
// Get display div from document
var display = document.getElementById("display");
// Instantiate client, using an HTTP tunnel for communications.
var guac = new Guacamole.Client(
  new Guacamole.HTTPTunnel("/api/tunnel")
);
// Add client to display div
display.appendChild(guac.getDisplay().getElement());

// Error handler
guac.onerror = function (error) {
  alert(error);
};
// Connect

guac.connect("id=" + id + "&type=" + type + "&width=" + (window.innerWidth - 20) + "&height=" + (window.innerHeight - 20));
// Disconnect on close
window.onunload = function () {
  guac.disconnect();
}
// Mouse
var mouse = new Guacamole.Mouse(guac.getDisplay().getElement());
mouse.onmousedown =
  mouse.onmouseup =
    mouse.onmousemove = function (mouseState) {
      guac.sendMouseState(mouseState);
    };
// Keyboard
var keyboard = new Guacamole.Keyboard(document);
keyboard.onkeydown = function (keysym) {
  guac.sendKeyEvent(1, keysym);
};
keyboard.onkeyup = function (keysym) {
  guac.sendKeyEvent(0, keysym);
};
/* ]]> */ </script>
</body>
</html>

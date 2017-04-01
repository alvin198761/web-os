<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>远程操作</title>

  <link rel="stylesheet" type="text/css" href="/guacamole/guacamole.css"/>
</head>
<body>
<!-- Display -->
<div id="display"></div>

<!-- Input abstractions -->
<script type="text/javascript" src="guacamole/ArrayBufferReader.js"></script>
<script type="text/javascript" src="guacamole/ArrayBufferWriter.js"></script>
<script type="text/javascript" src="guacamole/AudioPlayer.js"></script>
<script type="text/javascript" src="guacamole/BlobReader.js"></script>
<script type="text/javascript" src="guacamole/Client.js"></script>
<script type="text/javascript" src="guacamole/DataURIReader.js"></script>
<script type="text/javascript" src="guacamole/Display.js"></script>
<script type="text/javascript" src="guacamole/InputStream.js"></script>
<script type="text/javascript" src="guacamole/IntegerPool.js"></script>
<script type="text/javascript" src="guacamole/JSONReader.js"></script>
<script type="text/javascript" src="guacamole/Keyboard.js"></script>
<script type="text/javascript" src="guacamole/Layer.js"></script>
<script type="text/javascript" src="guacamole/Mouse.js"></script>
<script type="text/javascript" src="guacamole/Namespace.js"></script>
<script type="text/javascript" src="guacamole/Object.js"></script>
<script type="text/javascript" src="guacamole/OnScreenKeyboard.js"></script>
<script type="text/javascript" src="guacamole/OutputStream.js"></script>
<script type="text/javascript" src="guacamole/Parser.js"></script>
<script type="text/javascript" src="guacamole/Status.js"></script>
<script type="text/javascript" src="guacamole/StringReader.js"></script>
<script type="text/javascript" src="guacamole/StringWriter.js"></script>
<script type="text/javascript" src="guacamole/Tunnel.js"></script>
<script type="text/javascript" src="guacamole/Version.js"></script>
<script type="text/javascript" src="guacamole/VideoPlayer.js"></script>

<!-- Init -->
<script type="text/javascript"> /* <![CDATA[ */

// Get display div from document
var display = document.getElementById("display");

// Instantiate client, using an HTTP tunnel for communications.
var guac = new Guacamole.Client(new Guacamole.WebSocketTunnel("websocket-tunnel"));
// Add client to display div
display.appendChild(guac.getDisplay().getElement());

// Error handler
guac.onerror = function (error) {
  alert(error);
};

function GetRequest() {

  var url = location.search; //获取url中"?"符后的字串
  var theRequest = new Object();
  if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
      theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
    }
  }
  return theRequest;
}
Request = GetRequest();

var id = Request["id"];
var type= Request["type"];

guac.connect("id=" + 1 + "&type=" + type + "&width=" + (window.innerWidth - 20) + "&height=" + (window.innerHeight - 20));

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

window.addEventListener("resize",function () {
  document.execCommand('Refresh')
})

/* ]]> */ </script>
</body>
</html>

var argscheck = require ('cordova/argscheck')
var exec      = require ('cordova/exec')

module.exports = function () {
 var exports = {}
 
 exports.getURI = function (init) {
  var success = (typeof init.success != "undefined") ? init.success : function () {}
  var error   = (typeof init.error   != "undefined") ? init.error   : function () {}
  cordova.exec (success, error, "GooglePlayQuery", "getURI", [])
 }
 
 return exports
} ()

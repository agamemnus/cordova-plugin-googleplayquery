googleplayquery
==================

Using Javascript, get the query strings users installed your game with on Google Play.

Useful for adding special/secret install codes in your game (e.g.: for bonuses, etc.) and for tracking install campaigns.

Usage / Function List
----------------------

Get the query string array.
````
window.plugins.GooglePlayQuery.getURI ({
 success : function (result) {},
 error   : function (result) {}
})
````
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>GRUrl - Developer Info</title>
</head>
<body>
    <h2>Information For Developers</h2>
    <p>Would you like to know how you can request GRUrl to process URLs for you from your application?  Read on...</p>
    <div class="fancyBox">
        <h3>GET Request</h3>
        <p>All you need to do is pass one parameter along with your GET request to <span class='code'>http://grurl.net/rest/generate</span>...</p>
        <div class='code'>
            http://grurl.net/rest/generate?rawUrl=example.com/2008/12/31/its-new-years-eve
        </div>
        The response text will be the GRUrl-ified URL.
    </div>

    <div class="fancyBox">
        <h3>POST Request via XML</h3>
        <p>Just hit <span class='code'>http://grurl.net/rest/generate</span> with an XML request that looks like this:
        <div class='code'>
            <pre>&lt;rawUrl&gt;example.com/2008/12/31/its-new-years-eve&lt;/rawUrl&gt;</pre>
        </div>
        <p>Expect a response that looks like this:</p>
        <div class='code'>
            <pre>&lt;grurl&gt;http://grurl.net/3F6HL9&lt;/grurl&gt;</pre>
        </div>
    </div>
    
    <div class="fancyBox">
        <h3>GET Request to replace all URLs in a string</h3>
        <p>Pass one parameter along with your GET request to <span class='code'>http://grurl.net/rest/grurlifyString</span>...</p>
        <div class='code'>http://grurl.net/rest/grurlifyString?inputString=This+string+has+urls+here+is+one%3A+http%3A%2F%2Fwww.google.com+and+here+is+another%3A+http%3A%2F%2Fwww.example.com+and+there+they+are.
        </div>
        <p>Expect a response text that looks like this:</p>
        <div class='code'>
            This string has urls here is: http://grurl.net/1 and here is another: http://grurl.net/2 and there they are.
        </div>
    </div>
    
</body>
</html>

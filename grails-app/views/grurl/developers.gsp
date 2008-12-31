<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>GRUrl - Developer Info</title>
</head>
<body>
    <h3>Information For Developers</h3>
    <p>Would you like to know how you can request GRUrl to process URLs for you from your application?  Read on...</p>
    <h4>GET Request</h4>
    <p>All you need to do is pass one parameter along with your GET request to <span class='code'>http://grurl.com/generate</span>...</p>
    <div class='code'>
        http://grurl.com/generate?rawUrl=example.com/2008/12/31/its-new-years-eve
    </div>
    The response text will be the GRUrl-ified URL.
    
    <h4>POST Request via XML</h4>
    <p>Just hit <span class='code'>http://grurl.com/generate</span> with an XML request that looks like this:
    <div class='code'>
        <pre>&lt;rawUrl&gt;example.com/2008/12/31/its-new-years-eve&lt;/rawUrl&gt;</pre>
    </div>
    <p>Expect a response that looks like this:</p>
    <div class='code'>
        <pre>&lt;grurl&gt;http://grurl.com/3F6HL9&lt;/grurl&gt;</pre>
    </div>
</body>
</html>

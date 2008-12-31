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
        <p>All you need to do is pass one parameter along with your GET request to <span class='code'>http://grurl.net/generate</span>...</p>
        <div class='code'>
            http://grurl.net/generate?rawUrl=example.com/2008/12/31/its-new-years-eve
        </div>
        The response text will be the GRUrl-ified URL.
    </div>

    <div class="fancyBox">
        <h3>POST Request via XML</h3>
        <p>Just hit <span class='code'>http://grurl.net/generate</span> with an XML request that looks like this:
            <div class='code'>
                <pre>&lt;rawUrl&gt;example.com/2008/12/31/its-new-years-eve&lt;/rawUrl&gt;</pre>
            </div>
            <p>Expect a response that looks like this:</p>
            <div class='code'>
                <pre>&lt;grurl&gt;http://grurl.net/3F6HL9&lt;/grurl&gt;</pre>
            </div>
        </div>
    </body>
    </html>

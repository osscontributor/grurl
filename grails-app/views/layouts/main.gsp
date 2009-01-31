<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'main.css')}"/>
    <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'grurl.css')}"/>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
</head>
<body>
<div id="container">
    <div class="logo"><img src="${createLinkTo(dir: 'images', file: 'grurl_logo.png')}" alt="GRUrl"/></div>
    <div class="body">
        <g:render template="/templates/navigation"/>
		<div class="pageBody">
		    <p>
		        WARNING: GRUrl is currently in beta.  Data collected in GRUrl at this point may not be
		        migrated to the first production site.  It is recommended that you NOT refer to any
		        GRUrls in a tweet, email or anything else that may be long lived.  You have been warned.
		        <br/>
		        <br/>
		        <br/>
		        <br/>
		    </p>
        	<g:layoutBody/>
		</div>
    </div>
</div>
<g:render template="/templates/footer"/>
</body>
</html>
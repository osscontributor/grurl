<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>GRUrl</title>
</head>
<body>
	<h3>GRUrl - Your Source For Groovy URLs</h3>
	GRUrl will turn any ordinary URL into a Groovy URL (GRUrl).  A GRUrl is a shorter URL which may
	be used as an alias for a potentially longer URL.  See the <a href="${createLinkTo(dir: 'questions')}">questions</a> page for more information.
    <g:if test="${flash.message}">
        <div class="errors">${flash.message}</div>
    </g:if>

    <g:if test="${urlInstance?.id}">
        <g:render template='copyToClipboard' var='textToCopy' bean="${urlInstance.realUrl}"/>
        <div class="fancyBox">
        	<g:link action="redirectRequest" 
                 controller="grurl" 
                 params="[urlHash: urlInstance.id.encodeAsGrurl()]" 
                 absolute="true">
              ${createLink(action: 'redirectRequest', 
                           controller: 'grurl', 
                           params: [urlHash: urlInstance.id.encodeAsGrurl()], 
                           absolute: true)}
            </g:link> is now a GRUrl which may be used as an alias for ${urlInstance.realUrl}.
		</div>
    </g:if>
    <div class="fancyBox">
        <g:form action="generate" method="post">
            <label for="realUrl">Enter a URL to GRUrl-ify:</label>
            <input type="text" id="realUrl" name="realUrl" value=""/>
            <g:submitButton name="generate" value="Generate GRUrl"/>
        </g:form>
    </div>
</body>
</html>

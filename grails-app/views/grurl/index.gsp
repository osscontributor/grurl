<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>GRUrl</title>
</head>
<body>
    <h1>Welcome To GRUrl</h1>
    <g:if test="${flash.message}">
        <div class="errors">${flash.message}</div>
    </g:if>

    <g:if test="${urlInstance?.id}">
        <g:link action="redirectRequest" controller="grurl" params="[urlHash: Long.toString(urlInstance.id, Character.MAX_RADIX)]" absolute="true">
            ${createLink(action: 'redirectRequest', controller: 'grurl', params: [urlHash: Long.toString(urlInstance.id, Character.MAX_RADIX)], absolute: true)}
        </g:link>
    </g:if>
    <div class="inputArea">
        <g:form action="generate" method="post">
            <label for="realUrl">Enter a URL to GRUrl-ify:</label>
            <input type="text" id="realUrl" name="realUrl" value=""/>
            <g:submitButton name="generate" value="Generate GRUrl"/>
        </g:form>
    </div>
</body>
</html>

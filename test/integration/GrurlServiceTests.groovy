class GrurlServiceTests extends GroovyTestCase {
    
    def grurlService
    
    void testRefineWithNoProtocol() {
        def result = grurlService.refine('grails.org')
        assertEquals 'http://grails.org', result
    }
    
    void testRefineWithProtocol() {
        def result = grurlService.refine('http://groovy.codehaus.org')
        assertEquals 'http://groovy.codehaus.org', result
    }
    
    void testFindUrls_OneFullUrl() {
        def s = 'this is a test to find one url http://www.google.com and that was it'
        def urls = grurlService.findUrls(s)
        assertLength 1, urls
        assertEquals 'http://www.google.com', urls[0]
        s = 'this contains another url http://www.example.com right in here'
        urls = grurlService.findUrls(s)
        assertLength 1, urls
        assertEquals 'http://www.example.com', urls[0]
    }
    
    void testFindUrls_ManyFullUrls() {
        def s = 'this is a http://one.com test to find many url http://www.two.com and that was it'
        def urls = grurlService.findUrls(s)
        assertEquals 2, urls.size()
        assertEquals 'http://one.com', urls[0]
        assertEquals 'http://www.two.com', urls[1]
    }
    
    void testFindUrls_ManyComplexUrls() {
        def w = [
            'http://en.wikipedia.org/wiki/Octavia_(She-Ra)',
            'http://en.wikipedia.org/wiki/Jay_Peak_(Vermont)',
            'http://en.wikipedia.org/wiki/%C5%9Awiercze,_Lublin_Voivodeship'
        ]
        def s = "blah ${w[0]} blah ${w[1]} blah ${w[2]} blah"
        def urls = grurlService.findUrls(s)
        assertEquals 3, urls.size()
        w.eachWithIndex { it, i ->
            assertEquals it, urls[i]
        }
    }
    
    void testFindUrls_ManyComplexUrls_InLongNastyString() {
        def w = [
            'http://en.wikipedia.org/wiki/Octavia_(She-Ra)',
            'http://en.wikipedia.org/wiki/Jay_Peak_(Vermont)',
            'http://en.wikipedia.org/wiki/%C5%9Awiercze,_Lublin_Voivodeship'
        ]
        def s = """
        <div class="code"></div><br/><p class="paragraph"/>Decoding is performed using value.decodeHTML() syntax.<p class="paragraph"/><a name="Standard Codecs"></a><h2>Standard Codecs</h2><p class="paragraph"/>
        ${w[0]}<a name="HTMLCodec"></a><h3>HTMLCodec</h3><p class="paragraph"/>This codec perfoms HTML escaping and unescaping, so that values you provide can be rendered safely in an HTML page without creating any HTML tags or damaging the page layout. For example, given a value "Don't you know that 2 &#62; 1?" you wouldn't be able to show this safely within an HTML page because the &#62; will look like it closes a tag, which is especially bad if you render this data within an attribute, such as the value attribute of an input field.<p class="paragraph"/>Example of usage:
        <div class="code"><pre>&#60;input name=<span class="java&#45;quote">"comment.message"</span> value=<span class="java&#45;quote">"&#123;comment.message.encodeAsHTML()&#125;"</span>/&#62;</pre></div><br/><p class="paragraph"/>Note that the HTML encoding does not re-encode apostrophe/single quote so you must use double quotes on attribute values to avoid text with apostrophes messing up your page.<p class="paragraph"/><a name="URLCodec"></a><h3>URLCodec</h3><p class="paragraph"/>URL encoding is required when creating URLs in links or form actions, or any time data may be used to create a URL. It prevents illegal characters getting into the URL to change its meaning, for example a "Apple &#38; Blackberry" is not going to work well as a parameter in a GET request as the ampersand will break the parsing of parameters.<p class="paragraph"/>Example of usage:

        <div class="code"><pre>&#60;a href=<span class="java&#45;quote">"/mycontroller/find?searchKey=&#123;lastSearch.encodeAsURL()&#125;"</span>&#62;Repeat last search&#60;/a&#62;</pre></div><br/><p class="paragraph"/><a name="Base64Codec"></a><h3>Base64Codec</h3><p class="paragraph"/>Performs Base64 encode/decode functions.
        ${w[1]} Example of usage:
        <div class="code"><pre>Your registration code is: user.registrationCode.encodeAsBase64()</pre></div><br/><p class="paragraph"/><a name="JavaScriptCodec"></a><h3>JavaScriptCodec</h3><p class="paragraph"/>Example of usage:
        <div class="code"><pre>Element.update('&#123;elementId&#125;', '&#123;render(template: <span class="java&#45;quote">"/common/message"</span>).encodeAsJavaScript()&#125;')</pre></div><br/><p class="paragraph"/><a name="Custom Codecs"></a><h2>Custom Codecs</h2><p class="paragraph"/>Applications may define their own codecs and Grails will load them along with the standard codecs.  A custom codec class must be defined in the grails-app/utils/ directory and the class name must end with 'Codec'.  The codec may contain a static encode closure, a static decode closure or both.  The closure should expect a single argument which will be the object that the dynamic method was invoked on.

        <div class="code"><pre>class PigLatinCodec &#123;<p class="paragraph"/>  <span class="java&#45;keyword">static</span> encode = &#123; str &#45;&#62;<p class="paragraph"/>    // convert the string to piglatin and <span class="java&#45;keyword">return</span> the result<p class="paragraph"/>  &#125;<p class="paragraph"/>&#125;</pre></div><br/><p class="paragraph"/>With that codec in place an application could do something like this:
        ${w[2]}<div class="code"><pre>&#123;lastName.encodeAsPigLatin()&#125;</pre></div><br/>
        """
        
        def urls = grurlService.findUrls(s)
        assertEquals 3, urls.size()
        w.eachWithIndex { it, i ->
            assertEquals it, urls[i]
        }
    }
}
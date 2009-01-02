<script>
    function copyToClipboard(inElement) {
      if (inElement.createTextRange) {
        var range = inElement.createTextRange();
        if (range && BodyLoaded==1)
          range.execCommand('Copy');
      } else {
        var flashcopier = 'flashcopier';
        if(!document.getElementById(flashcopier)) {
          var divholder = document.createElement('div');
          divholder.id = flashcopier;
          document.body.appendChild(divholder);
        }
        document.getElementById(flashcopier).innerHTML = '';
        var divinfo = '<embed src="${createLinkTo(dir:'/flash', file:'_clipboard.swf')}" FlashVars="clipboard='+encodeURIComponent(inElement.innerHTML)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';
        document.getElementById(flashcopier).innerHTML = divinfo;
      }
    }
</script>

<div id='textToCopyEl' style='display:none'>${textToCopy}</div>

<script>
    copyToClipboard(document.getElementById('textToCopyEl'));
</script>
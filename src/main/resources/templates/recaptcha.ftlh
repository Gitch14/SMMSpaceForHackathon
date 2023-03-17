<#import "blocks/template.ftlh" as t>
<@t.template user>
    <script type="text/javascript" src="../static/js/script.js"></script>
    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
            async defer>
    </script>
    <div class="content-center">
    <h1 style="text-align: center">Registration</h1>
    <hr>
        <form method="post" action="/api/recaptcha">
            <div class="g-recaptcha" data-sitekey="<6LfL5AklAAAAAJjykUK-YBxKG6VPBMNjkGcPbaFV>"></div>
            <button type="submit">Submit</button>
        </form>

    </div>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>
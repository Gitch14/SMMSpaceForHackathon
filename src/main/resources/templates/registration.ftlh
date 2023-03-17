<#import "blocks/template.ftlh" as t>
<@t.template user>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script>
        function checkRecaptcha() {
            var response = grecaptcha.getResponse();
            if (response.length === 0) {
                alert('Вы не прошли проверку reCAPTCHA.');
                return false;
            } else {
                return true;
            }
        }
    </script>
    <div class="content-center">
        <h1 style="text-align: center">Registration</h1>
        <hr>
        <form action="/registration" method="post" onsubmit="return checkRecaptcha();">
            <div class="form-group">
                <label for="name">User Name</label>
                <input type="text" class="form-control" required id="name" name="name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" required name="email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" required name="password">
            </div>
            <div class="g-recaptcha" data-sitekey="6LdF-gklAAAAAJ6ziq0SV6AQT24IRV7i7PQgkUN-"></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Sign Up</button>
        </form>
    </div>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>

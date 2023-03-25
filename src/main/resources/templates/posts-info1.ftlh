<link rel="stylesheet" href="/static/css/slider.css">
<#import "blocks/template.ftlh" as t>
<@t.template user>
    <!--
    <#list images as image>
            <a href="${image.path}/${image.name}">
                <img class="post" style="height: 330px; width: 586px" src="${image.path}/${image.name}" alt="${image.originalFileName}">
            </a>
    </#list>
    -->

    <div class="slideshow-container">

        <!-- Full-width images with number and caption text -->
         <#list images as image>
        <div class="mySlides fade">
            <a href="${image.path}/${image.name}">
                <img class="post" style="height: 330px; width: 586px" src="${image.path}/${image.name}" alt="${image.originalFileName}">
            </a>
        </div>
         </#list>

        <!-- Next and previous buttons -->
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>
    <div>
    <p><h1 style="text-align: left;">${post.title}</h1></p>


    <p style="text-align: text-align: left;">
        <a href="/user/${authorPost.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorPost.name}
        </a>
    </p>
    <hr>
    <p style="text-align: center">${post.description}</p></div>

<!--
    <div> <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <#list images as image>
                    <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                </#list>
            </ol>
            <div class="carousel-inner">
                <#list images as image>
                    <div class="carousel-item <#if image.previewImage>active</#if>">
                        <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                             role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title></title>
                            <rect width="100%" height="100%" fill="#777"></rect>
                            <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                        </svg>

                        <div class="container">
                            <a href="${image.path}/${image.name}">
                                <img class="post" style="height: 330px; width: 586px" src="${image.path}/${image.name}" alt="${image.originalFileName}">
                            </a>
                        </div>
                    </div>
                </#list>

            </div>
            <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <hr>
        <p><h1 style="text-align: left;">${post.title}</h1></p>


        <p style="text-align: text-align: left;">
            <a href="/user/${authorPost.id}">
                <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorPost.name}
            </a>
        </p>
        <hr>
        <p style="text-align: center">${post.description}</p></div>
-->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/static/js/script1.js"></script>
    <script src="/static/js/slick.min.js"></script>
    <script src="/static/js/slick.js"></script>

</@t.template>
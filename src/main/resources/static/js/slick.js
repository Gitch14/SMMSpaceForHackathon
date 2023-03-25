// $(document).ready(function () {
// 	$('.slider').slick({
// 		arrows: true,
// 		dots:true,
// 		adaptiveHeight:true,
// 		slidesToShow:1,
// 		slidesToScroll:1,
// 		speed:300,
// 		infinite:true,
// 		initialSlide:0,
// 		autoplay:true,
// 		autoplaySpeed:3000,
// 		centerMode: true,
// 		variableWidth: true,
// 		pauseOnFocus:true,
// 		pauseOnHover:true,
// 		pauseOnDotsHover:true,
// 	});
// });

let slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
showSlides(slideIndex = n);
}

function showSlides(n) {
let i;
let slides = document.getElementsByClassName("mySlides");
let dots = document.getElementsByClassName("dot");
if (n > slides.length) {slideIndex = 1}
if (n < 1) {slideIndex = slides.length}
for (i = 0; i < slides.length; i++) {
	slides[i].style.display = "none";
}
for (i = 0; i < dots.length; i++) {
	dots[i].className = dots[i].className.replace(" active", "");
}
slides[slideIndex-1].style.display = "block";
dots[slideIndex-1].className += " active";
}

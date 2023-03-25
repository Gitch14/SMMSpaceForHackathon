/*----------------Search--------------------------------------------*/
const searchRef = document.querySelector(".search");

searchRef.addEventListener('click', handlerSearch);

const ulRef = document.querySelector('.header__menu');


const liRef = document.querySelector('.menu__item-search');

function handlerSearch() {
	searchRef.hidden = true;

	ulRef.style = "margin-left:330px";

	const inputRef = document.createElement('input');
	inputRef.classList = 'search__input';
	liRef.append(inputRef);

	const newSearch = document.createElement('img');
	newSearch.src = "./header/search2.svg";
	newSearch.style = "margin-left:-35px;margin-top:-3px;";
	liRef.append(newSearch);
}


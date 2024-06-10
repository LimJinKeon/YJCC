const textInput = document.getElementById('tag');
const suggestions = document.getElementById('suggestions');
const btn = document.querySelector("#find");

const xhr = new XMLHttpRequest();

textInput.addEventListener('input', function() {
    const inputText = textInput.value;

    xhr.open("POST", "/findTags", true);
    xhr.setRequestHeader("content-type", "text/plain");
    xhr.send(inputText);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let suggestionList = [];
            suggestions.innerHTML = '';

            let data = JSON.parse(xhr.responseText);
            data.forEach(tag => {
                suggestionList.push(tag);
            });

            if (inputText) {
                const filteredSuggestions = suggestionList.filter(item =>
                    item.includes(inputText)
                );
                filteredSuggestions.forEach(item => {
                    const suggestionItem = document.createElement('div');
                    suggestionItem.textContent = item;
                    suggestionItem.addEventListener('click', function () {
                        textInput.value = item;
                        suggestions.style.display = 'none';
                    });
                    suggestions.appendChild(suggestionItem);
                });
                suggestions.style.display = filteredSuggestions.length ? 'block' : 'none';

            } else {
                suggestions.style.display = 'none';
            }
        }
    }
});

document.addEventListener('click', function(event) {
    if (event.target !== textInput) {
        suggestions.style.display = 'none';
    }
});

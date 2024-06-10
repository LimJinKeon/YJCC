var existingTags = [];
var list = document.querySelectorAll(".tag span");
list.forEach(function(tagElement) {
    var tagText = tagElement.textContent.trim();
    existingTags.push(tagText.substring(1));
});


let xhr = new XMLHttpRequest();

document.getElementById('add').addEventListener('click', function() {

    var tagInput = document.getElementById('tag').value.trim();

    if (tagInput !== '') {
        // 이미 기록된 태그인지 확인
        if (existingTags.indexOf(tagInput) == -1) {
            var tagList = document.getElementById('tagList');
            var tagElement = document.createElement('span');
            tagElement.textContent = '#' + tagInput;
            tagList.appendChild(tagElement);

            document.getElementById('tag').value = '';

            existingTags.push(tagInput);

            saveTag(tagInput);
        } else {
            swal({
                title: "중복된 태그입니다",
                icon: "error",
            });
            document.getElementById('tag').value = '';
        }
    }
});

function saveTag(tag) {
    xhr.open("POST", "/tag", true);
    xhr.setRequestHeader("content-type", "text/plain");
    xhr.send(tag);
}

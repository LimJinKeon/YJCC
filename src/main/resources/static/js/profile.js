const input = document.querySelector("#imageInput"); // 파일 입력(input) 요소 가져오기

function showImage(event) {
    var reader = new FileReader();

    reader.onload = function() {
        var dataURL = reader.result;
        var preview = document.getElementById('preview');
        preview.src = dataURL;
        preview.style.display = 'block';
    };
    reader.readAsDataURL(input.files[0]);
}

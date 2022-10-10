(() => {
  var preview_img = function (file) {
    var reader = new FileReader(); // 用來讀取檔案
    reader.readAsDataURL(file); // 讀取檔案

    reader.addEventListener("load", function () {
          let img_src =
          '<img src="' + reader.result + '" class="preview_img">';
          document.querySelector(".preview").innerHTML = img_src
    });
  };
 

})();
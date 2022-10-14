const { createApp } = Vue;

createApp({
    data() {
        return {
            message: "Hello Vue.js",
            preview: null,
            image: null,
            preview_list: [],
            image_list: []
        };
    },
    methods: {
        previewImage(event) {
            var input = event.target;
            if (input.files) {
                var reader = new FileReader();
                reader.onload = (e) => {
                    this.preview = e.target.result;
                }
                this.image = input.files[0];
                reader.readAsDataURL(input.files[0]);
            }
        },
        reset() {
            this.image = null;
            this.preview = null;
            this.image_list = [];
            this.preview_list = [];
        },
        exbAdd() {
            console.log("按");

            let exhibitionName = document.querySelector("#exhibitionName").value
            let exhibitionStartDate = document.querySelector("#start_date").value
            let exhibitionEndDate = document.querySelector("#end_date").value
            let exhibitionType = document.querySelector("#exhibitionType").value
            let exhibitionStatus = document.querySelector("#exhibitionStatus").value
            let exhibitionArticle = document.querySelector("#exhibitionArticle").value
            let valueAldult = document.querySelector("#valueAldult").value
            let valueStu = document.querySelector("#valueStu").value
            let valueOld = document.querySelector("#valueOld").value
            let valuePhy = document.querySelector("#valuePhy").value
            let img = document.querySelector("#my-file")
            var reader = new FileReader(); // 用來讀取檔案
            reader.readAsDataURL(img.files[0]); // 讀取檔案
            reader.addEventListener("load", function () {
                fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionInsert', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        exhibitionName,
                        exhibitionStartDate,
                        exhibitionEndDate,
                        exhibitionType,
                        exhibitionStatus,
                        exhibitionArticle,
                        img: reader.result.replace("data:image/jpeg;base64,", ""),
                        valueAldult,
                        valueStu,
                        valueOld,
                        valuePhy
                    })
                })
                    .then(resp => resp.json())
                    .then(R => {
                        alert("新增成功^_^!");
                    })
            });

        },
    },
}).mount("#app");

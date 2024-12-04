
    function calculateShippingCost() {
    // Lấy giá trị từ các trường nhập liệu
    const weight = parseFloat(document.getElementById('weight').value) || 0;
    const length = parseFloat(document.querySelector('input[placeholder="R"]').value) || 0;
    const width = parseFloat(document.querySelector('input[placeholder="D"]').value) || 0;
    const height = parseFloat(document.querySelector('input[placeholder="C"]').value) || 0;

    // Tính toán thể tích
    const volume = length * width * height; // cm³

    // Định nghĩa phí
    let totalShippingCost;

    // Tính tổng chi phí vận chuyển dựa trên cân nặng và thể tích
    if (weight < 2 && volume < 1000) {
    totalShippingCost = 31000; // VNĐ
} else {
    totalShippingCost = 41000; // VNĐ
}

    // Hiển thị kết quả
    document.getElementById('shippingCost').value = totalShippingCost.toLocaleString();
}

    // Gọi hàm tính phí khi có thay đổi trong các trường nhập liệu
    document.querySelectorAll('input[type="text"]').forEach(input => {
    input.addEventListener('input', calculateShippingCost);
});



    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();

    function triggerFileUpload() {
        document.getElementById('fileUpload').click();
    }

    function previewMultipleImages(input) {
        const container = document.getElementById('upload-preview-container');


        Array.from(input.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.classList.add('img-preview'); // Thêm class để áp dụng CSS
                container.appendChild(img);
            };
            reader.readAsDataURL(file);
        });

        //
    }




    function showModal() {
        document.getElementById('categoryModal').style.display = 'flex';
    }

    function closeModal() {
        document.getElementById('categoryModal').style.display = 'none';
    }

    function selectCategory(categoryID, categoryName) {
        // Cập nhật giá trị cho productType
        document.getElementById('productType').value = categoryName; // Cập nhật tên danh mục
        document.getElementById('selectedCategoryID').value = categoryID; // Cập nhật ID nếu cần
        closeModal(); // Đóng modal
    }


    function confirmSelection() {
        // Xử lý xác nhận nếu cần
        alert('Đã chọn: ' + document.getElementById('productType').value);
        closeModal();
    }

    function showModal() {
        // Mở modal hoặc logic hiển thị ngành hàng (không được cung cấp ở đây)
        document.getElementById('categoryModal').style.display = 'flex';
        // Sau khi người dùng chọn ngành hàng:
        const selectedType = "Ngành hàng đã chọn"; // Thay thế với ngành hàng thực tế, có thể lấy từ modal
        document.getElementById('productType').value = selectedType; // Cập nhật giá trị vào input ẩn
    }



    function fetchSubcategories(parentId) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "fetchSubcategories?parent_id=" + parentId, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var subcategories = JSON.parse(xhr.responseText);
                console.log(subcategories); // Kiểm tra dữ liệu
                displaySubcategories(subcategories);
            }
        };
        xhr.send();
    }

    function displaySubcategories(subcategories) {
        var subcategoryList = document.getElementById("subcategories");
        subcategoryList.innerHTML = ""; // Reset danh sách trước

        subcategories.forEach(function (sub) {
            var li = document.createElement("li");
            li.onclick = function () {
                selectCategory(sub.categoryID, sub.categoryName); // Gọi hàm và truyền cả ID và tên
            };
            li.innerText = sub.categoryName; // Hiển thị tên danh mục
            subcategoryList.appendChild(li);
        });
    }

    function scrollToContent(contentId) {
        // Lấy phần nội dung cụ thể
        const contentElement = document.getElementById(contentId);

        if (contentElement) {
            // Cuộn tới phần nội dung
            contentElement.scrollIntoView({behavior: 'smooth', block: 'start'});
        }
    }




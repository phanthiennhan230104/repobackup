const form = document.getElementById("orderForm");

form.addEventListener("submit", function (e) {
    e.preventDefault(); // Ngừng reload trang

    const formData = new FormData(form);

    // Gửi yêu cầu đến backend
    fetch("/website/submit", {
        method: "POST",
        body: formData,
    })
    .then((response) => response.json())
    .then((data) => {
        console.log('Response:', data); // Kiểm tra dữ liệu phản hồi

        if (data.status) {
            // Hiển thị thông báo thành công
            document.getElementById("successMessage").innerText = data.message;
            document.getElementById("successDialog").style.display = "block";
        } else {
            // Hiển thị thông báo lỗi
            alert('Có lỗi xảy ra, vui lòng thử lại!');
        }
    })
    .catch((error) => {
        console.error("Error submitting form:", error);
        alert('Có lỗi xảy ra, vui lòng thử lại!');
    });
});

// Đóng modal khi click vào dấu x
document.querySelector('.close').addEventListener('click', function() {
    document.getElementById("successDialog").style.display = "none";
});
/**
 * 
 */
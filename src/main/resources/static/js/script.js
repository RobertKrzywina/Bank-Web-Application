function clicked(e, msg) {
    if (!confirm('Are you sure u want to ' + msg + '?')) e.preventDefault();
}

function successAlert(title, text, icon) {
    swal({
        title: title,
        text: text,
        icon: icon,
        button: "Submit"
    })
}

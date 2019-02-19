function clicked(e, msg) {
    if (!confirm('Are you sure u want to ' + msg + '?')) e.preventDefault();
}

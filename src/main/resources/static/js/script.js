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

function changeLanguage(language) {
    var lastLanguage = window.location.href.substring(window.location.href.length-2, window.location.href.length);
    if (language === 'pl' && lastLanguage !== 'pl') {

        if (lastLanguage === 'en') {
            window.location.href = window.location.href.replace('en', 'pl');
        } else if (lastLanguage === 'de') {
            window.location.href = window.location.href.replace('de', 'pl');
        } else {
            window.location.search += "lang=pl";
        }

    } else if (language === 'en' && lastLanguage !== 'en') {

        if (lastLanguage === 'pl') {
            window.location.href = window.location.href.replace('pl', 'en');
        } else if (lastLanguage === 'de') {
            window.location.href = window.location.href.replace('de', 'en');
        } else {
            window.location.search += "lang=en";
        }

    } else if (language === 'de' && lastLanguage !== 'de') {

        if (lastLanguage === 'en') {
            window.location.href = window.location.href.replace('en', 'de');
        } else if (lastLanguage === 'pl') {
            window.location.href = window.location.href.replace('pl', 'de');
        } else {
            window.location.search += "lang=de";
        }
    }
}

function trimIt() {
    $(function () {
        $('input[type="text"]').change(function () {
            this.value = $.trim(this.value);
        });
    });
}

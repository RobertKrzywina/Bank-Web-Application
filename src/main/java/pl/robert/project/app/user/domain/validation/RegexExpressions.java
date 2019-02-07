package pl.robert.project.app.user.domain.validation;

public interface RegexExpressions {
    String phoneNumberRegex = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)";
}

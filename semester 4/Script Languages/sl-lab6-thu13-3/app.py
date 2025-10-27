import pytest


class Request:
    def __init__(self, requestType, path, protocolType):
        self.requestType = requestType
        self.path = path
        self.protocolType = protocolType

class BadHTTPVersion(Exception):
    pass

class BadRequestTypeError(Exception):
    pass


def reqstr2obj(request_string):
    allowed=["GET", "POST", "PUT", "DELETE",
    "HEAD", "OPTIONS", "PATCH",
    "TRACE", "CONNECT"]
    if isinstance(request_string,str):
        list=request_string.split(" ")
        if(len(list)==3):
            if(list[0] in allowed):  #there are more but idk
                if(list[2] in ["HTTP1.0", "HTTP1.1", "HTTP2.0"]):
                    if(list[1][0]=='/'):
                        return Request(list[0], list[1], list[2])
                    else:
                        raise ValueError("Path must start with /")
                else:
                    raise BadHTTPVersion("this HTTP version is not supported")
            else:
                raise BadRequestTypeError("this request type is not allowed")
        else:
            return None
    else:
        raise TypeError("request_string must be a string")

#1
def test_typeError():
    with pytest.raises(TypeError):
        reqstr2obj(12)

# 2
def test_returns_HTTP_object():
    result = reqstr2obj("GET / HTTP1.1")
    assert isinstance(result, Request)


# 3
def test_attributes_set():
    result = reqstr2obj("GET / HTTP1.1")
    assert result.requestType == "GET"
    assert result.path == "/"
    assert result.protocolType == "HTTP1.1"


# 4
@pytest.mark.parametrize("input_string,expected", [
    ("POST /login HTTP1.1", ("POST", "/login", "HTTP1.1")),
    ("PUT /api/data HTTP2.0", ("PUT", "/api/data", "HTTP2.0")),
    ("DELETE /item/1 HTTP1.0", ("DELETE", "/item/1", "HTTP1.0")),
])
def test_multiple_valid_inputs(input_string, expected):
    result = reqstr2obj(input_string)
    assert (result.requestType, result.path, result.protocolType) == expected

# 5
@pytest.mark.parametrize("bad_input", [
    "GET /", "GET", "GET/", "/ HTTP1.1", "GET//HTTP1.1"
])
def test_invalid_format_returns_none(bad_input):
    assert reqstr2obj(bad_input) is None

# 6
def test_illegal_request():
    with pytest.raises(BadRequestTypeError):
        reqstr2obj("DOWNLOAD /file HTTP1.1")


# 7
def test_invalid_http_version():
    with pytest.raises(BadHTTPVersion):
        reqstr2obj("GET / HTTP5.0")


# 8
def test_path_must_start_with_slash():
    with pytest.raises(ValueError, match="Path must start with /"):
        reqstr2obj("GET homepage HTTP1.1")


print("This is the lab6 assignment solution")


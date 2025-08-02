#include <stdio.h> 
#include <curl/curl.h> 
#include <string> 
#include <curl/curl.h>
#include <iostream>
#include "utils.h"

void fazerPostJson(const std::string& url, const std::string& jsonstr) {
    CURL* curl = curl_easy_init();
    if (!curl) {
        std::cerr << "Erro ao iniciar cURL" << std::endl;
        return;
    }

    struct curl_slist* headers = nullptr;
    headers = curl_slist_append(headers, "Content-Type: application/json");

    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonstr.c_str());
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
    curl_easy_setopt(curl, CURLOPT_USERAGENT, "curl/7.38.0");

    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK)
        std::cerr << "Erro ao enviar POST: " << curl_easy_strerror(res) << std::endl;

    curl_slist_free_all(headers);
    curl_easy_cleanup(curl);
}

int main(void) 
{  
	CURLcode ret; 
    CURL *hnd; 
    struct curl_slist *slist1; 
    std::string jsonstr = "{\"username\":\"aied\",\"password\":\"123456\"}"; 
  
    slist1 = NULL; 
    slist1 = curl_slist_append(slist1, "Content-Type: application/json"); 
  
    hnd = curl_easy_init(); 
    if( hnd ) { 
		curl_easy_setopt(hnd, CURLOPT_URL, "url"); 
		curl_easy_setopt(hnd, CURLOPT_NOPROGRESS, 1L); 
		curl_easy_setopt(hnd, CURLOPT_POSTFIELDS, jsonstr.c_str()); 
		curl_easy_setopt(hnd, CURLOPT_USERAGENT, "curl/7.38.0"); 
		curl_easy_setopt(hnd, CURLOPT_HTTPHEADER, slist1); 
		curl_easy_setopt(hnd, CURLOPT_MAXREDIRS, 50L); 
		curl_easy_setopt(hnd, CURLOPT_CUSTOMREQUEST, "POST"); 
		curl_easy_setopt(hnd, CURLOPT_TCP_KEEPALIVE, 1L); 
		ret = curl_easy_perform(hnd);
		curl_easy_cleanup(hnd); 
		hnd = NULL; 
		curl_slist_free_all(slist1); 
		slist1 = NULL;
	}
}
		
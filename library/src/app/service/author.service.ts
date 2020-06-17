import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import{Observable} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private ip = "http://192.168.145.100";
  // private authorUrl = this.ip +":8080/author"
  private authorUrl = "http://localhost:8080/author"


  constructor(
    private http:HttpClient
  ) { }
  getAuthor(id: number): Observable<any> {
    return this.http.get(`${this.authorUrl}/${id}`);  
   
  }

  createAuthor(author: Object): Observable<Object> {
    return this.http.post(`${this.authorUrl}/create/`, author);
  }

  updateAuthor(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.authorUrl}/${id}`, value);
  }

  deleteAuthor(id: number): Observable<any> {
    return this.http.put(`${this.authorUrl}/remove/${id}`, { responseType: 'text' });
  }

  getAuthorList(): Observable<any> {
   return  this.http.get(`${this.authorUrl}`) 
  }
}

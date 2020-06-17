import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import{Observable} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private ip = "http://192.168.145.100";
  // private commentUrl = this.ip +":8080/comment"
  private commentUrl = "http://localhost:8080/comment"


  constructor(
    private http:HttpClient
  ) { }
  getAComment(id: number): Observable<any> {
    return this.http.get(`${this.commentUrl}/${id}`);  
   
  }

  createComment(comment: Object): Observable<Object> {
    return this.http.post(`${this.commentUrl}/create`, comment);
  }
 
  deleteAuthor(id: number): Observable<any> {
    return this.http.put(`${this.commentUrl}/remove/${id}`, { responseType: 'text' });
  }

  getCommentList(): Observable<any> {
   return  this.http.get(`${this.commentUrl}`) 
  }
}

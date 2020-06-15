import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/service/author.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-createlist-a',
  templateUrl: './createlist-a.component.html',
  styleUrls: ['./createlist-a.component.css']
})
export class CreatelistAComponent implements OnInit {
  p:number=1
  author:Observable<Author[]>
  constructor(
    private router :Router,
    private authorService: AuthorService
    ) { }

  ngOnInit() {
    this.reloadData();
  }
  reloadData(){
    this.author=this.authorService.getAuthorList()
    console.log( this.author)
  }
  createAuthor(){
    console.log("detail")
    this.router.navigate(['admin/createAuthor']);
  }
  EditAuthor(id:number){
    console.log("edit",id)
  
    this.router.navigate(['admin/editAuthor',id]);
  }
  ListAuthor(id:number){
    console.log("list")
    this.router.navigate(['admin/listAuthor',id]);
  }
  DeleteAuthor(id:number){
    console.log(id) ;
    this.author =this.authorService.deleteAuthor(id);
    console.log(id," test id");
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Delete successful',
      showConfirmButton: false,
      timer: 2500,
    }); 
    location.reload();
}
}

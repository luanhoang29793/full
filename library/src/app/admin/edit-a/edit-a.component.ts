import { Component, OnInit } from '@angular/core';
import { Author } from 'src/app/model/author';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorService } from 'src/app/service/author.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-edit-a',
  templateUrl: './edit-a.component.html',
  styleUrls: ['./edit-a.component.css']
})
export class EditAComponent implements OnInit {
  id:number;
  author :Author =new Author;

  constructor(
    private route : ActivatedRoute,
    private router : Router,
    private authorService : AuthorService
  ) { }

  ngOnInit() {
    this.id= this.route.snapshot.params['id'];
   this.authorService.getAuthor(this.id)
   .subscribe(data => {
     console.log(data)
     this.author = data;
   }, error => console.log(error));  
  }
  updateAuthor() {
    this.authorService.updateAuthor(this.id, this.author)
      .subscribe((data) => {Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: ' Saved successful',
        showConfirmButton: false,
        timer: 1500
        
      });
      this.ngOnInit();
     }, error => Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: ' Saved Failure',
        showConfirmButton: false,
        timer: 1500
      }));
    this.author = new Author();
    }
  onSubmit() {
    this.updateAuthor();   
  }
  listAuthor(){
    this.router.navigate(['admin/author']);
  }

}

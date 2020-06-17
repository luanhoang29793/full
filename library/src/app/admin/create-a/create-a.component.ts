import { Component, OnInit } from '@angular/core';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/service/author.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-a',
  templateUrl: './create-a.component.html',
  styleUrls: ['./create-a.component.css']
})
export class CreateAComponent implements OnInit {
  author: Author = new Author();
  submitted = false;
  constructor(    
    private authorService: AuthorService,
    private router: Router

    ) { }

  ngOnInit() {
  }
  save() {
    this.authorService.createAuthor(this.author)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Saved successful',
      showConfirmButton: false,
      timer: 1500
    });
    this.ngOnInit();
   
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Saved Failure',
      showConfirmButton: false,
      timer: 1500
    });    
      this.author = new Author();
  }
  onSubmit() {
    this.submitted = true;
    this.save();    
  }
  listAuthor() {
    console.log("ve list")
    this.router.navigate(['admin/author']);
  }
}

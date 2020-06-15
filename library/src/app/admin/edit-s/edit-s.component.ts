import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';
import { error } from 'util';
import { Category } from 'src/app/model/category';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/service/author.service';
import { CategoryService } from 'src/app/service/category.service';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-s',
  templateUrl: './edit-s.component.html',
  styleUrls: ['./edit-s.component.css']
})
export class EditSComponent implements OnInit {
id: number;
book: Book = new Book;
category: Observable< Category>;
author: Observable< Author>;
  constructor(
    private route :ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private authorService: AuthorService,
    private categoryService: CategoryService
     
  ) { }

  ngOnInit() {
    this.book = new Book();
    this.author= this.authorService.getAuthorList();
    this.category= this.categoryService.getCategoryList();
    this.id = this.route.snapshot.params['id'];
    this.bookService.getBook(this.id)
      .subscribe(data => {
        console.log(data)
        this.book = data;
      }, error => console.log(error));
  }
  updateBook() {
    this.bookService.updateBook(this.id, this.book)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Create successful',
      showConfirmButton: false,
      timer: 1500
    });
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: ' Saved Failure',
      showConfirmButton: false,
      timer: 1500
    });

      this.book = new Book();
      this.ngOnInit();
  }
  onSubmit() {
    this.updateBook();    
  }
  listStory(){
    this.router.navigate(['admin/listStory']);
  }
  onSelectFile(event :any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event:any) => { // called once readAsDataURL is completed
        this.book.nameImage = event.target.result as string;
        console.log(event,"event")
      }
    }
  }
}

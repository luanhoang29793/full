import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';
import { Observable } from 'rxjs';
import { Author } from 'src/app/model/author';
import { BookService } from 'src/app/service/book.service';
import { AuthorService } from 'src/app/service/author.service';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import Swal from 'sweetalert2';
import { Upload } from 'src/app/model/upload';
import { UploadFileService } from 'src/app/service/upload.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';



@Component({
  selector: 'app-create-s',
  templateUrl: './create-s.component.html',
  styleUrls: ['./create-s.component.css']
})
export class CreateSComponent implements OnInit {
  book :Book = new Book();
  author:Observable<Author[]>
  category:Observable<Category[]>
  submitted =false;
  url = '';

  selectedFiles: FileList;
  message = '';
  currentFile: File;
  progress = 0;
  files: Observable<Upload[]>

  public Editor = ClassicEditor;




  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private categoryService: CategoryService,
    private router: Router,

    private uploadService: UploadFileService,
  ) { }

  ngOnInit() {
    this.reload();
  }
  reload(){
    this.author=this.authorService.getAuthorList();
    this.category = this.categoryService.getCategoryList();
    this.files = this.uploadService.getFiles();  
  }
  newClass(): void {
    this.submitted = false;
    this.book = new Book();
  }
  save() {
    this.book.nameImage= this.url;
    console.log(this.url)
    console.log(this.book,"book1")

    this.bookService.createBook(this.book)
       .subscribe((data) => {Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: ' Create successful',
        showConfirmButton: false,
        timer: 1500
      });
      console.log(this.book)
    }), error => Swal.fire({
        position: 'top-end',
        icon: 'error',
        title: ' Saved Failure',
        showConfirmButton: false,
        timer: 1500
      });
    this.book = new Book();

  }
  onSubmit() {
    this.submitted = true;
    this.save();

  }
  listStory(){
    this.router.navigate(['admin/listStory']);
  }
  onSelectFile(event :any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event:any) => { // called once readAsDataURL is completed
        this.url = event.target.result as string;
        console.log(event,"event")
      }
    }
    this.selectedFiles = event.target.files;

  }
  // selectFile(event) {
  //   this.selectedFiles = event.target.files;
  // }
  upload(id:number){
  this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile,id).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.files = this.uploadService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

}


import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { CommentService } from 'src/app/service/comment.service';
import { Comment } from 'src/app/model/comment';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comment:Comment = new Comment();
  comment1: Comment= new Comment();
  submitted = false;
  constructor(    
    private commentService: CommentService,

    ) { }

  ngOnInit() {
    this.commentService.getCommentList()
    .subscribe(data => {
      console.log(data)
      this.comment1 = data;
    }, error => console.log(error));
  }
  

  save() {
    console.log(this.comment,"new")
    this.commentService.createComment(this.comment)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Create successful',
      showConfirmButton: false,
      timer: 1500
      
    });
    this.ngOnInit(); }),
     error => Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Create Failure',
      showConfirmButton: false,
      timer: 1500
    });
  this.comment = new Comment();

  }
  onSubmit() {
    this.submitted =true
    this.save();    
  }
  
}

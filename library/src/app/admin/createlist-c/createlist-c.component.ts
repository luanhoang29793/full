import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable} from 'rxjs';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import Swal from 'sweetalert2'



@Component({
  selector: 'app-createlist-c',
  templateUrl: './createlist-c.component.html',
  styleUrls: ['./createlist-c.component.css']
})
export class CreatelistCComponent implements OnInit {
  p:number=1

  category:Observable<Category[]>
  constructor(
    private router: Router,
   
    private categoryService : CategoryService,
) {
     }
   

    ngOnInit() {
      this.reloadData();
    
    }
  reloadData(){
    console.log("reloade data")
    this.category = this.categoryService.getCategoryList()
  }
  createCategory(){
    console.log("create")
    this.router.navigate(['admin/createCategory']);
  }
  EditCategory(id:number){
    console.log("edit")
  
    this.router.navigate(['admin/editCategory',id]);
  }
  ListCategory(id:number){
    console.log("list")
    this.router.navigate(['use/listStoryCategory',id]);
  }
  DeleteCategory(id:number){
    console.log("list")
    
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.category =this.categoryService.deleteCategory(id);
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
      }
    })
      

  }
 

} 

import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-list-category-id',
  templateUrl: './list-category-id.component.html',
  styleUrls: ['./list-category-id.component.css']
})
export class ListCategoryIdComponent implements OnInit {

  category:Observable<Category[]>
  constructor(
    private router: Router,
    private categoryService : CategoryService) { }

    ngOnInit() {
      this.reloadData();
    }
  reloadData(){
    console.log("reloade data")
    this.category = this.categoryService.getCategoryList()
  }
  listStoryCategory(id: number){
    console.log(id);
    this.router.navigate(['user/listStoryCategory',id]);
  }


}

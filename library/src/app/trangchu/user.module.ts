import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'
import { IndexComponent } from './index/index.component';
import { HeaderIDComponent } from './header-id/header-id.component';
import { ListStorynewComponent } from './list-storynew/list-storynew.component';
import { FooterIDComponent } from './footer-id/footer-id.component';
import { ListStoryComponent } from './list-story/list-story.component';
import { ImageSlideComponent } from './image-slide/image-slide.component';
import { ListCategoryIdComponent } from './list-category-id/list-category-id.component';
import { ChapterComponent } from '../trangchu/chapter/chapter.component';
import { ListStoryCategoryComponent } from './list-story-category/list-story-category.component';
import { ListChapterComponent } from './list-chapter/list-chapter.component';

const routes = [
  {
    path: '',
    component: IndexComponent
  },
  {
    path: 'listStoryCategory/:id',
    component: ListStoryCategoryComponent
  },
  {
    path: 'listChapter/:id',
    component: ListChapterComponent
  },
  {
    path: 'Chapter/:id',
    component: ChapterComponent
  },
  

]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes,)
  ],
  declarations: [IndexComponent,
    HeaderIDComponent,
    ListStorynewComponent,
    FooterIDComponent,
    ListStoryComponent,
    ImageSlideComponent,
    ListCategoryIdComponent,
    ChapterComponent,
    ListStoryCategoryComponent,
    ListChapterComponent,]
})
export class UserModule { }
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllEntitiesComponent } from './components/entity/all-entities/all-entities.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserComponent } from './user.component';
import { SaveEntityComponent } from './components/entity/save-entity/save-entity.component';
import { PageNotFoundComponent } from 'src/app/page-not-found/page-not-found.component';
import { SaveDirectorComponent } from './components/director/save-director/save-director.component';
import { AllDirectorsComponent } from './components/director/all-directors/all-directors.component';


const routes: Routes = [
  {
    path:'user',
    component: UserComponent,
    children: [
      {path:'dashboard', component: DashboardComponent},


      /* entities */
      {path:'entities', component: AllEntitiesComponent},
     /*  {path:'entities/add', component: AddEntityComponent}, */
      {path:'entities/form', component: SaveEntityComponent},
      
      /* {path:'entities/:id/edit', component: EditEntityComponent}, */


      /* directors */
      {path:'directors', component: AllDirectorsComponent},
      {path:'directors/save', component: SaveDirectorComponent},


      { path: '404',  component: PageNotFoundComponent },
      { path: '**',  redirectTo: '/404' },
    ]
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule { }

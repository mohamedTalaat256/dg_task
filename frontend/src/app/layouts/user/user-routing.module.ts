import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllEntitiesComponent } from './components/entity/all-entities/all-entities.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserComponent } from './user.component';
import { AddEntityComponent } from './components/entity/add-entity/add-entity.component';
import { AddPhoneModalComponent } from './components/global/add-phone-modal/add-phone-modal.component';
import { EditEntityComponent } from './components/entity/edit-entity/edit-entity.component';
import { PageNotFoundComponent } from 'src/app/page-not-found/page-not-found.component';
import { AddDirectorComponent } from './components/director/add-director/add-director.component';


const routes: Routes = [
  {
    path:'user',
    component: UserComponent,
    children: [
      {path:'dashboard', component: DashboardComponent},


      /* entities */
      {path:'entities', component: AllEntitiesComponent},
      {path:'entities/add', component: AddEntityComponent},
      {path:'entities/:id/edit', component: EditEntityComponent},


      /* directors */
      {path:'directors', component: AllEntitiesComponent},
      {path:'directors/add', component: AddDirectorComponent},
      {path:'directors/:id/edit', component: EditEntityComponent},


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

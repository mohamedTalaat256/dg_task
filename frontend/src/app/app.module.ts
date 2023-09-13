import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule, provideAnimations } from '@angular/platform-browser/animations';
import { HeaderComponent } from './layouts/user/components/global/header/header.component';

import { SideNavbarComponent } from './layouts/user/components/global/side-navbar/side-navbar.component';
import { FooterComponent } from './layouts/user/components/global/footer/footer.component';
import { DashboardComponent } from './layouts/user/components/dashboard/dashboard.component';
import { ScrollToTopComponent } from './layouts/user/components/global/scroll-to-top/scroll-to-top.component';
import { ProfileComponent } from './layouts/user/components/profile/profile.component';
import { NgbCollapse, NgbCollapseModule, NgbDatepickerModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AllEntitiesComponent } from './layouts/user/components/entity/all-entities/all-entities.component';
import { AddEntityComponent } from './layouts/user/components/entity/add-entity/add-entity.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './layouts/auth/components/login/login.component';
import { RegisterComponent } from './layouts/auth/components/register/register.component';
import { AuthComponent } from './layouts/auth/auth.component';
import { UserComponent } from './layouts/user/user.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthRoutingModule } from './layouts/auth/auth-routing.module';
import { UserRoutingModule } from './layouts/user/user-routing.module';
import { FormsModule } from '@angular/forms';
import { DemoNumber } from './pips/demoNumber.pipe';
import { AddDirectorComponent } from './layouts/user/components/director/add-director/add-director.component';
import { AddPhoneModalComponent } from './layouts/user/components/global/add-phone-modal/add-phone-modal.component';
import { EditEntityComponent } from './layouts/user/components/entity/edit-entity/edit-entity.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AuthInterceptor } from './service/auth.interceptor';

import { provideToastr } from 'ngx-toastr';


@NgModule({
    declarations: [
        DemoNumber,
        AppComponent,
        HeaderComponent,
        FooterComponent,
        DashboardComponent,
        ScrollToTopComponent,
        ProfileComponent,
        SideNavbarComponent,
        AllEntitiesComponent,
        AddEntityComponent,
        LoginComponent,
        RegisterComponent,
        AuthComponent,
        UserComponent,
        AddDirectorComponent,
        AddPhoneModalComponent,
        EditEntityComponent,
        PageNotFoundComponent
    ],
    providers: [

        {
            provide: HTTP_INTERCEPTORS,
            useClass:AuthInterceptor,
            multi: true
        },
        provideAnimations(), // required animations providers
        provideToastr(), // Toastr providers
    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AuthRoutingModule,
        UserRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        NgbCollapse,
        NgbCollapseModule,
        NgbModule,
        FormsModule,
	    NgbDatepickerModule
    ]
})
export class AppModule {}

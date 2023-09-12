import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-add-phone-modal',
  templateUrl: './add-phone-modal.component.html',
  styleUrls: ['./add-phone-modal.component.css']
})
export class AddPhoneModalComponent {

  constructor(private modalService: NgbModal){}







  openModalAddPhone(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }


  onSubmit(f: NgForm) {
    console.log(f.value);
    this.modalService.dismissAll();
  }

}

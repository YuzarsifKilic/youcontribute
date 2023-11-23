import {Component, OnInit} from '@angular/core';
import {Repository} from "../_models/repository";
import {RepositoryService} from "../services/repository.service";
import {ToastrService} from "ngx-toastr";
import { faBug } from '@fortawesome/free-solid-svg-icons';
import {OneSignal} from "onesignal-ngx";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  repositories: Repository[] = [];
  loading = false;
  faBug = faBug;

  constructor(private repositoryService: RepositoryService,
              private toastr: ToastrService,
              private os: OneSignal) {
    this.os.init({
      appId: "3ba2ffdc-d019-48f0-8bbf-dd7ea47c1312",
    });
  }

  ngOnInit(): void {

        this.list();
    }

  list() {
    this.repositoryService.list()
      .subscribe(resp => {
        this.loading = false;
        this.repositories = resp;
      },
        error => {
                this.loading = false;
                this.toastr.error(error.error.message, "Error")
        })
  }

}

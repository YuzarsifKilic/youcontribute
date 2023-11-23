import {Component, OnInit} from '@angular/core';
import {Challenge} from "../../_models/challenge";
import {IssueService} from "../../_services/issue.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute} from "@angular/router";
import {ChallengeService} from "../../_services/challenge.service";

@Component({
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrl: './challenge-list.component.css'
})
export class ChallengeListComponent implements OnInit {

  challenges: Challenge[] = [];
  loading = false;

  constructor(private challengeService: ChallengeService,
              private toastr: ToastrService,) { }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.loading = true;
    this.challengeService.list()
      .subscribe(resp => {
        this.loading = false;
        this.challenges = resp;
      }, error => {
        this.toastr.error(error, "Error");
        this.loading = false;
      })
  }

}

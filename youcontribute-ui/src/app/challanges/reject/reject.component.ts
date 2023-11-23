import {Component, OnInit} from '@angular/core';
import {ChallengeService} from "../../_services/challenge.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs";

@Component({
  selector: 'app-reject',
  templateUrl: './reject.component.html',
  styleUrl: './reject.component.css'
})
export class RejectComponent implements OnInit {

  loading = false;

  constructor(private challengeService: ChallengeService,
              private route: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.reject(params["id"]);
    })
  }

  private reject(id: number) {
    this.loading = true;
    this.challengeService.reject(id)
      .pipe(first())
      .subscribe((resp) => {
        this.loading = false;
        this.toastr.success("Challenge rejected successfully", "Success");
        setTimeout(() => {
          this.router.navigate(["challenges"])
        })
      }, error =>  {
        this.loading = false;
        this.toastr.error(error.error.message, "Error");
      });
  }

}

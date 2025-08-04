// webapp/js/common.js
$(function(){
    // ====== 1) 배너 슬라이더 ======
    let currentIndex = 1;
    const totalSlides = 5;
    const $slide = $('#bannerSlide');
    const $images = $slide.find('img');
    const getImgWidth = () => $images.first().width();

    function updateSlide(animated = true) {
        const imgWidth = getImgWidth();
        if (!animated) {
            $slide.css('transition', 'none');
        } else {
            $slide.css('transition', 'transform 0.5s ease-in-out');
        }
        $slide.css('transform', `translateX(-${currentIndex * imgWidth}px)`);
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % totalSlides;
        updateSlide(true);
        if (currentIndex === totalSlides - 1) {
            setTimeout(function(){
                currentIndex = 1;
                updateSlide(false);
            }, 500);
        }
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        updateSlide(true);
        if (currentIndex === 0) {
            setTimeout(function(){
                currentIndex = totalSlides - 2;
                updateSlide(false);
            }, 500);
        }
    }

    $('.main-banner-next').on('click', nextSlide);
    $('.main-banner-prev').on('click', prevSlide);

    $(window).on('pageshow', function(event) {
        if (event.originalEvent.persisted) {
            location.reload();
        }
    });

    updateSlide(false);

    // ====== 2) 사이드바 토글 ======
    const $leftAd = $('.left-ad-container');

    $('.nav-category-menu').on('click', function(e) {
        e.stopPropagation();
        $('.sidebar, .sidebar-overlay').toggleClass('open');
        $('.sidebar-overlay').toggle();
        $leftAd.toggle(!$('.sidebar').hasClass('open'));
    });

    $('.sidebar-overlay').on('click', function() {
        $('.sidebar').removeClass('open');
        $(this).hide();
        $leftAd.show();
    });


	// ====== 4) FAQ 페이지 전용 로직 (수정됨) ======   <---- 여기부터 교체된 내용
	// faq.jsp 페이지에 <div class="faq-page"> 요소가 있을 때만 실행됩니다.
	if ($('.faq-page').length) {

	    // 즉시 실행 함수 (IIFE)로 감싸 변수 스코프를 더 명확히 제한 (선택 사항이지만 권장)
	    (function() {

	        // 초기화: 페이지 로드 시 모든 답변 숨기기
	        $('.faq-answer').hide();

	        // 질문(.faq-question) 클릭 시 아코디언 토글 기능
	        $('.faq-question').on('click', function() {
	            const $clickedQuestion = $(this);
	            const $clickedAnswer = $clickedQuestion.next('.faq-answer');

	            // 다른 열려있는 질문/답변 닫기 (클릭된 질문 제외)
	            $('.faq-question.active').not($clickedQuestion).each(function() {
	                $(this).removeClass('active');
	                $(this).next('.faq-answer').slideUp();
	            });

	            // 현재 클릭된 질문의 active 클래스를 토글
	            $clickedQuestion.toggleClass('active');

	            // 현재 클릭된 답변을 부드럽게 토글
	            $clickedAnswer.slideToggle();
	        });

	        // 카테고리 탭(.category-tabs a) 클릭 시 FAQ 목록 필터링 기능
	        $('.category-tabs a').on('click', function(e) {
	            e.preventDefault();

	            const $clickedTab = $(this);
	            const selectedCategory = $clickedTab.data('category');

	            // 모든 탭에서 'active' 클래스 제거
	            $('.category-tabs a').removeClass('active');
	            // 클릭된 탭에만 'active' 클래스 추가
	            $clickedTab.addClass('active');

	            // 모든 FAQ 항목(.faq-item)을 순회하며 필터링
	            $('.faq-item').each(function() {
	                const $item = $(this);
	                const itemCategory = $item.data('category');
	                const shouldShow = selectedCategory === 'all' || String(itemCategory) == String(selectedCategory);
	                $item.toggle(shouldShow);
	            });
	        });

	    })(); 


        // 카테고리 탭 클릭 → 필터링
        $('.category-tabs a').on('click', function(e){
            e.preventDefault();
            const $tab = $(this);
            const cat = $tab.data('category');
            $('.category-tabs a').removeClass('active');
            $tab.addClass('active');
            $('.faq-item').each(function(){
                const match = cat === 'all' || $(this).data('category') == cat;
                $(this).toggle(match);
            });
        });
    }

    // ====== 5) 문의 상세 페이지(inquirydetail.jsp) 전용 로직 ======
    if ($('.inquiry-detail-page').length) {
        // 삭제
        $('#btnDelete').on('click', function(){
            if (confirm("정말 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.")) {
                $('#deleteForm').submit();
            }
        });

        // 답변 등록
        $('#btnSubmitReply').on('click', function(){
            const inqId = $(this).data('inquiry-id');
            const content = $('#replyContent').val().trim();
            if (!content) { alert("답변 내용을 입력해주세요."); return; }

            $.post(contextPath + '/cs/inquiry/' + inqId + '/reply', { inqryRply: content })
                        .done(function(res) {
                            if (res.success) {
                                alert("답변이 등록되었습니다.");
                                location.reload();
                            } else {
                                alert(res.message || "답변 등록에 실패했습니다.");
                            }
                        })
                        .fail(function(_, __, err) {
                            alert("서버 오류: " + err);
                        });
                });

                // 상태 변경
                $('#btnStatusUpdate').on('click', function() {
                    const inqId = $('#statusSelect').data('inquiry-id');
                    const st = $('#statusSelect').val();

                    $.post(contextPath + '/cs/inquiry/' + inqId + '/status', { status: st })
                        .done(function(res) {
                            if (res.success) {
                                alert("상태가 변경되었습니다.");
                                location.reload();
                            } else {
                                alert(res.message || "상태 변경에 실패했습니다.");
                            }
                        })
                        .fail(function() {
                            alert("서버 오류가 발생했습니다.");
                        });
                });

                // 답변 수정 폼 토글
                $('#showEditForm').on('click', function() {
                    const current = $('.reply-content p').text();
                    $('#editReplyContent').val(current);
                    $('.reply-content, #showEditForm').hide();
                    $('#replyEditForm').insertAfter('.reply-content').show();
                });

                // 답변 수정 저장
                $('#saveReplyEdit').on('click', function() {
                    const inqId = $(this).data('inquiry-id');
                    const updated = $('#editReplyContent').val().trim();
                    if (!updated) {
                        alert("답변 내용을 입력해주세요.");
                        return;
                    }

                    $.post(contextPath + '/cs/inquiry/' + inqId + '/reply', { inqryRply: updated })
                        .done(function(res) {
                            if (res.success) {
                                alert("답변이 수정되었습니다.");
                                location.reload();
                            } else {
                                alert(res.message || "답변 수정에 실패했습니다.");
                            }
                        })
                        .fail(function() {
                            alert("서버 오류가 발생했습니다.");
                        });
                });

                // 답변 수정 취소
                $('#cancelReplyEdit').on('click', function() {
                    $('#replyEditForm').hide();
                    $('.reply-content, #showEditForm').show();
                });

                // 환불 처리 (관리자용)
                $('#btnRefund').on('click', function() {
                    const impUid = $('#imp_uid').val().trim();
                    if (!impUid) {
                        alert("IMP UID를 입력해주세요.");
                        return;
                    }
                    if (!confirm("정말 환불하시겠습니까?")) {
                        return;
                    }

                    $.post(contextPath + '/refund/process', { imp_uid: impUid })
                        .done(function() {
                            alert("환불 요청이 처리되었습니다.");
                            location.reload();
                        })
                        .fail(function() {
                            alert("환불 요청 중 오류가 발생했습니다.");
                        });
                });
            }

            // ====== 6) 문의하기(inquiry-form) 페이지 전용 로직 ======
            if ($('.inquiry-form-page').length) {
                const MAX_FILE_SIZE = 30 * 1024 * 1024;
                const ALLOWED_EXTS = ['jpg', 'jpeg', 'png'];
                const ALLOWED_MIME = ['image/jpeg', 'image/jpg', 'image/png'];
                const $fileInput = $('#imageFile');
                const $fileTypeError = $('#fileTypeError');
                const $fileSizeError = $('#fileSizeError');
                const $previewContainer = $('#imagePreviewContainer');
                const $previewImage = $('#imagePreview');
                const $submitBtn = $('#submitButton');
                const $modal = $('#exchangeRefundModal');
                const $exchangeGuide = $('#exchangeGuide');
                const $refundGuide = $('#refundGuide');
                const $modalTitle = $('#modalTitle');
                const $modalClose = $('.close-btn');
                const $modalConfirm = $('#modalConfirmBtn');

                // 파일 선택 검증 & 미리보기
                $fileInput.on('change', function() {
                    $fileTypeError.hide();
                    $fileSizeError.hide();
                    $previewContainer.hide();
                    const file = this.files[0];
                    if (!file) return;

                    let valid = true;
                    const ext = file.name.split('.').pop().toLowerCase();
                    if (!ALLOWED_EXTS.includes(ext) || !ALLOWED_MIME.includes(file.type)) {
                        $fileTypeError.slideDown();
                        valid = false;
                    }
                    if (file.size > MAX_FILE_SIZE) {
                        $fileSizeError.slideDown();
                        valid = false;
                    }
                    $submitBtn.prop('disabled', !valid);
                    if (valid) {
                        const reader = new FileReader();
                        reader.onload = e => {
                            $previewImage.attr('src', e.target.result);
                            $previewContainer.slideDown();
                        };
                        reader.readAsDataURL(file);
                    }
                });

                // 폼 제출 전 최종 검증
                $('form').on('submit', function(e) {
                    const file = $fileInput[0].files[0];
                    if (file) {
                        let ok = true;
                        const ext = file.name.split('.').pop().toLowerCase();
                        if (!ALLOWED_EXTS.includes(ext) || !ALLOWED_MIME.includes(file.type)) {
                            $fileTypeError.slideDown();
                            ok = false;
                        }
                        if (file.size > MAX_FILE_SIZE) {
                            $fileSizeError.slideDown();
                            ok = false;
                        }
                        if (!ok) {
                            e.preventDefault();
                            $submitBtn.prop('disabled', true);
                        }
                    }
                });

                // 교환/환불 안내 모달
                $('#inqryCtgry').on('change', function() {
                    const v = $(this).val();
                    if (v === '1' || v === '2') {
                        if (v === '1') {
                            $modalTitle.text('교환 안내');
                            $exchangeGuide.show();
                            $refundGuide.hide();
                        } else {
                            $modalTitle.text('환불 안내');
                            $exchangeGuide.hide();
                            $refundGuide.show();
                        }
                        $modal.fadeIn(300);
                    }
                });

                // 초기 로드 시 (수정 모드)
                const initV = $('#inqryCtgry').val();
                if (initV === '1' || initV === '2') {
                    setTimeout(() => $('#inqryCtgry').trigger('change'), 500);
                }

                // 모달 닫기
                $modalClose.on('click', () => $modal.fadeOut(300));
                $modalConfirm.on('click', () => $modal.fadeOut(300));
                $(window).on('click', function(e) {
                    if ($(e.target).is($modal)) {
                        $modal.fadeOut(300);
                    }
                });
            }

            // ====== 7) 문의목록(inquiry-list) 페이지 전용 로직 ======
            if ($('.inquiry-list-page').length) {
                // 정렬 변경 함수 전역 등록
                window.changeSort = function(sortValue) {
                    const params = new URLSearchParams(window.location.search);
                    params.set('sort', sortValue);
                    params.set('page', 1);
                    if (params.has('myInquiries')) {
                        params.set('myInquiries', 'true');
                    }
                    window.location.href = window.location.pathname + '?' + params.toString();
                };

                // 내 문의글 토글 전역 등록
                window.toggleMyInquiries = function(showMy) {
                    const params = new URLSearchParams(window.location.search);
                    if (showMy) {
                        params.set('myInquiries', 'true');
                    } else {
                        params.delete('myInquiries');
                    }
                    params.set('page', 1);
                    window.location.href = window.location.pathname + '?' + params.toString();
                };

                // 테이블 행 클릭 → 상세로 이동
                $('.inquiry-table tbody tr').on('click', function(e) {
                    if ($(e.target).is('a') || $(e.target).closest('a').length) {
                        return;
                    }
                    const href = $(this).find('.title-cell a').attr('href');
                    if (href) {
                        window.location.href = href;
                    }
                });

                $('.inquiry-table tbody tr').css('cursor', 'pointer');

                // 검색창 엔터키로 제출
                $('.search-bar input[type="text"]').on('keypress', function(e) {
                    if (e.key === 'Enter') {
                        e.preventDefault();
                        $(this).closest('form').submit();
                    }
                });

                // 내 문의글 버튼
                $('.my-inquiry-btn').on('click', function(e) {
                    if (!$(this).hasClass('login-required')) {
                        e.preventDefault();
                        toggleMyInquiries(true);
                    }
                });

                // 다른 계정이 쓴 비밀글 누르면 알림창
                if ( $('.inquiry-list-page').length > 0 ) {
                        const $errorDiv = $("#error-message");
                        if ($errorDiv.length > 0) {
                            const errorMessage = $errorDiv.data("message");
                            if (errorMessage && errorMessage.trim().length > 0) {
                                alert(errorMessage);
                            }
                        }
                    }

                // 카테고리 탭 클릭 시 myInquiries 유지
                $('.category-tabs a').on('click', function(e) {
                    const href = $(this).attr('href');
                    const params = new URLSearchParams(window.location.search);
                    if (params.get('myInquiries') === 'true') {
                        e.preventDefault();
                        window.location.href = href + '&myInquiries=true';
                    }
                });

                // 로그인 모달 닫기
                $(document).on('click', '#closeLoginModal', function() {
                    $('#loginModal').hide();
                });

                // 로그인 폼 AJAX 제출
                $(document).on('submit', '#loginForm', function(e) {
                    e.preventDefault();
                    const data = {
                        mbrInsId: $("#loginForm input[name='mbrInsId']").val(),
                        mbrPwd:   $("#loginForm input[name='mbrPwd']").val()
                    };

                    $.ajax({
                        url:         contextPath + "/member/ajax-login",
                        method:      "POST",
                        contentType: "application/json",
                        data:        JSON.stringify(data)
                    })
                    .done(function(res) {
                        if (res.loggedIn) {
                            $("#loginModal").hide();
                            location.reload();
                        } else {
                            $("#login-error")
                              .text("아이디 또는 비밀번호를 확인하세요.")
                              .show();
                        }
                    })
                    .fail(function() {
                        $("#login-error")
                          .text("서버 오류 발생")
                          .show();
                    });
                });

                // 로그인 필요 버튼 클릭
                $('.login-required').on('click', function() {
                    $.get(contextPath + "/member/check-login")
                      .done(function(res) {
                         
                         if (!res.loggedIn) {
                              $("#loginModal").show();
                          }
                      })
                      .fail(function() {
                          alert("서버와 통신 중 오류 발생");
                      });
                });
            }
            
            // ====== 8) 로그인(login.jsp) 페이지 전용 로직 ======
            if (document.querySelector('.login-container')) {
                // 1) 회원가입 완료 알림
                const params = new URLSearchParams(window.location.search);
                if (params.get('registSuccess') === 'true') {
                    alert('회원가입이 완료되었습니다!');
                }

                // 2) 로그인 폼 검증 함수 전역 등록
                window.validateLoginForm = function() {
                    const idVal = document.querySelector('input[name="mbrInsId"]').value.trim();
                    const pwVal = document.querySelector('input[name="mbrPwd"]').value.trim();
                    document.getElementById('idError').textContent = '';
                    document.getElementById('pwError').textContent = '';
                    let ok = true;
                    if (!idVal) {
                        document.getElementById('idError').textContent = '아이디를 입력해주세요.';
                        ok = false;
                    }
                    if (!pwVal) {
                        document.getElementById('pwError').textContent = '비밀번호를 입력해주세요.';
                        ok = false;
                    }
                    return ok;
                };

                // 3) 로그인 페이지 진입 시 redirect 자동 세팅
                const redirect = params.get('redirect'); // ★ 수정: nextUrl → redirect
                if (redirect) {
                    const redirectInput = document.getElementById('redirect'); // ★ 수정: nextUrl → redirect
                    if (redirectInput) {
                        redirectInput.value = redirect;
                    }
                }
            }

            
              // ====== 8) 회원정보 수정(modify.jsp) 페이지 전용 로직 ======
                if ( $('.modify-container').length ) {
                  // ① 페이지 로드 시 버튼 초기 상태 & 탈퇴 완료 알림
                  const params = new URLSearchParams(window.location.search);
                  if ( params.get('deleted') === 'true' ) {
                    alert('회원 탈퇴가 완료되었습니다.');
                  }
                  $('#verifyButton, #submitBtn').prop('disabled', true);

                  // ② 폼 검증 함수 (onsubmit="return validateForm()")
                  window.validateForm = function() {
                    const pw = $('input[name="mbrPwd"]').val();
                    const phone = $('#mbrPhNo').val().trim();
                    let ok = true;

                    $('#pwdError, #phoneError').text('');

                    if ( pw && !/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,20}$/.test(pw) ) {
                      $('#pwdError').text('비밀번호는 대소문자, 숫자, 특수문자 포함 8~20자');
                      ok = false;
                    }
                    if ( !/^010-\d{4}-\d{4}$/.test(phone) ) {
                      $('#phoneError').text('휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.');
                      ok = false;
                    }
                    return ok;
                  };

                  // ③ 실시간 전화번호 유효성 검사
                  window.validatePhoneNumber = function() {
                    const phone = $('#mbrPhNo').val().trim();
                    if ( /^010-\d{4}-\d{4}$/.test(phone) ) {
                      $('#phoneError').text('유효한 형식입니다.').removeClass('error-msg').addClass('success-msg');
                      return true;
                    } else {
                      $('#phoneError').text('휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.').removeClass('success-msg').addClass('error-msg');
                      return false;
                    }
                  };
                  $('#mbrPhNo').on('input', validatePhoneNumber);

                  // ④ 우편번호 검색 (Daum Postcode)
                  window.sample6_execDaumPostcode = function() {
                    new daum.Postcode({
                      oncomplete: function(data) {
                        let addr = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
                        let extra = '';
                        if (data.userSelectedType === 'R') {
                          if (data.bname && /[동|로|가]$/.test(data.bname)) extra += data.bname;
                          if (data.buildingName && data.apartment==='Y') extra += (extra? ', '+data.buildingName: data.buildingName);
                          if (extra) extra = ' ('+extra+')';
                        }
                        $('#sample6_postcode').val(data.zonecode);
                        $('#sample6_address').val(addr);
                        $('#sample6_extraAddress').val(extra);
                        $('#sample6_detailAddress').focus();
                      }
                    }).open();
                  };

                  // ⑤ 이메일 인증
                  window.sendAuthCode = function() {
                    const email = $('input[name="mbrEmail"]').val().trim();
                    if (!email) { alert('이메일을 입력해주세요.'); return; }
                    fetch(contextPath + '/member/email-auth?email=' + encodeURIComponent(email), { method:'POST', credentials:'include' })
                      .then(_=>{ alert('인증 코드가 전송되었습니다.'); $('#verifyButton').prop('disabled', false); })
                      .catch(_=>{ alert('인증 코드 전송에 실패했습니다.'); });
                  };

                  window.verifyCode = function() {
                    const email = $('input[name="mbrEmail"]').val().trim();
                    const code  = $('input[name="emailCode"]').val().trim();
                    if (!email||!code) { alert('이메일과 인증 코드를 모두 입력해주세요.'); return; }
                    fetch(contextPath + '/member/verify-code', {
                      method: 'POST',
                      headers:{'Content-Type':'application/x-www-form-urlencoded'},
                      credentials:'include',
                      body: `mbrEmail=${encodeURIComponent(email)}&emailCode=${encodeURIComponent(code)}`
                    })
                    .then(r=>r.text())
                    .then(result=>{
                      if (result==='success') {
                        alert('이메일 인증이 완료되었습니다.');
                        $('#submitBtn').prop('disabled', false);
                      } else {
                        alert('인증 코드가 일치하지 않습니다.');
                      }
                    })
                    .catch(_=>{ alert('인증 확인 실패.'); });
                  };
                }
                // ====== 9) 회원가입(regist.jsp) 페이지 전용 로직 ======
                  if ( $('.regist-container').length ) {
                    // 버튼 초기화
                    $('#verifyButton, #submitBtn').prop('disabled', true);

                    let isIdValid = false;

                    // 아이디 중복 확인
                    window.checkIdDuplication = function(){
                      const idVal = $('input[name="mbrInsId"]').val().trim();
                      const $err = $('#idError');
                      const idReq = /^[a-z][a-z0-9_]{4,19}$/;
                      $err.text('').removeClass('error-msg success-msg');
                      if (!idReq.test(idVal)) {
                        $err.text('아이디는 영문 소문자로 시작, 5~20자 (숫자, 밑줄 허용)')
                            .addClass('error-msg');
                        isIdValid = false;
                        return;
                      }
                      fetch(contextPath + '/member/check-id?mbrInsId=' + encodeURIComponent(idVal))
                        .then(r=>r.text())
                        .then(result=>{
                          if (result==='exist') {
                            $err.text('이미 사용 중인 아이디입니다.').addClass('error-msg');
                            isIdValid = false;
                          } else {
                            $err.text('사용 가능한 아이디입니다.').addClass('success-msg');
                            isIdValid = true;
                          }
                        })
                        .catch(_=>{
                          $err.text('중복 확인 중 오류가 발생했습니다.').addClass('error-msg');
                          isIdValid = false;
                        });
                    };

                    // 비밀번호 일치 확인
                    window.validatePasswordMatch = function(){
                      const pw  = $('input[name="mbrPwd"]').val();
                      const chk = $('input[name="mbrPwdCheck"]').val();
                      const $err = $('#pwdCheckError');
                      $err.text('').removeClass('error-msg success-msg');
                      if (pw && pw===chk)  $err.text('비밀번호가 일치합니다.').addClass('success-msg');
                      else                  $err.text('비밀번호가 일치하지 않습니다.').addClass('error-msg');
                    };

                    // 이메일 인증코드 전송
                    window.sendAuthCode = function(){
                      const email = $('input[name="mbrEmail"]').val().trim();
                      if (!email) { alert('이메일을 입력해주세요.'); return; }
                      fetch(contextPath + '/member/email-auth?email=' + encodeURIComponent(email), {
                        method:'POST', credentials:'include'
                      })
                      .then(_=>{ alert('인증 코드가 전송되었습니다.'); $('#verifyButton').prop('disabled', false); })
                      .catch(_=>{ alert('인증 코드 전송에 실패했습니다.'); });
                    };

                    // 인증코드 확인
                    window.verifyCode = function(){
                      const email = $('input[name="mbrEmail"]').val().trim();
                      const code  = $('input[name="emailCode"]').val().trim();
                      if (!email||!code) { alert('이메일과 인증 코드를 모두 입력해주세요.'); return; }
                      fetch(contextPath + '/member/verify-code',{
                        method:'POST',
                        headers:{'Content-Type':'application/x-www-form-urlencoded'},
                        credentials:'include',
                        body:`mbrEmail=${encodeURIComponent(email)}&emailCode=${encodeURIComponent(code)}`
                      })
                      .then(r=>r.text())
                      .then(res=>{
                        if (res==='success') {
                          alert('이메일 인증이 완료되었습니다.');
                          $('#submitBtn').prop('disabled', false);
                        } else if (res==='timeout') {
                          alert('⏰ 인증 시간이 초과되었습니다. 다시 인증을 요청해주세요.');
                          $('#verifyButton').prop('disabled', true);
                        } else {
                          alert('인증 코드가 일치하지 않습니다.');
                        }
                      })
                      .catch(_=>{ alert('인증 확인 실패.'); });
                    };

                    // 전화번호 실시간 유효성 검사
                    window.validatePhoneNumber = function(){
                      const val = $('#mbrPhNo').val().trim();
                      const $err = $('#phoneError');
                      const pat = /^010-\d{4}-\d{4}$/;
                      $err.text('').removeClass('error-msg success-msg');
                      if (pat.test(val)) $err.text('유효한 형식입니다.').addClass('success-msg');
                      else               $err.text('휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.').addClass('error-msg');
                    };
                    $('#mbrPhNo').on('input', validatePhoneNumber);

                    // 닉네임 유효성 검사
                    window.validateNickname = function(){
                      const v = $('input[name="mbrNcknm"]').val();
                      const $err = $('#ncknmError');
                      $err.text('').removeClass('error-msg');
                      if (v.length>20) {
                        $err.text('닉네임은 20자 이내로 입력해주세요.').addClass('error-msg');
                      }
                    };
                    $('input[name="mbrNcknm"]').on('input', validateNickname);

                    // Daum Postcode
                    window.sample6_execDaumPostcode = function(){
                      new daum.Postcode({
                        oncomplete(data){
                          let addr = data.userSelectedType==='R'?data.roadAddress:data.jibunAddress;
                          let extra = '';
                          if (data.userSelectedType==='R'){
                            if (data.bname&&/[동|로|가]$/.test(data.bname)) extra+=data.bname;
                            if (data.buildingName&&data.apartment==='Y')
                              extra += (extra? ', '+data.buildingName: data.buildingName);
                          }
                          $('#sample6_postcode').val(data.zonecode);
                          $('#sample6_address').val(addr);
                          $('#sample6_extraAddress').val(extra);
                          $('#sample6_detailAddress').focus();
                        }
                      }).open();
                    };

                    // 최종 폼 검증 (onsubmit)
                    window.validateForm = function(){
                      const id   = $('input[name="mbrInsId"]').val().trim();
                      const pw   = $('input[name="mbrPwd"]').val();
                      const chk  = $('input[name="mbrPwdCheck"]').val();
                      const nick = $('input[name="mbrNcknm"]').val();
                      const phone= $('#mbrPhNo').val().trim();
                      let ok = true;

                      // 에러 영역 초기화
                      $('#idError, #pwdError, #pwdCheckError, #ncknmError, #phoneError').text('');

                      if (!isIdValid) {
                        $('#idError').text('아이디 중복 확인을 해주세요.').addClass('error-msg');
                        ok = false;
                      }
                      if (!/^[a-z][a-z0-9_]{4,19}$/.test(id)) {
                        $('#idError').text('아이디 형식이 올바르지 않습니다.').addClass('error-msg');
                        ok = false;
                      }
                      if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,20}$/.test(pw)) {
                        $('#pwdError').text('비밀번호는 대소문자, 숫자, 특수문자 포함 8~20자').addClass('error-msg');
                        ok = false;
                      }
                      if (pw!==chk) {
                        $('#pwdCheckError').text('비밀번호가 일치하지 않습니다.').addClass('error-msg');
                        ok = false;
                      }
                      if (nick.length>20) {
                        $('#ncknmError').text('닉네임은 20자 이내로 입력해주세요.').addClass('error-msg');
                        ok = false;
                      }
                      if (!/^010-\d{4}-\d{4}$/.test(phone)) {
                        $('#phoneError').text('휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.').addClass('error-msg');
                        ok = false;
                      }
                      return ok;
                    };
                  }
                  if ( location.pathname.indexOf('/mypage/activity') !== -1 ) {
                      // 페이지 로드 시
                      const saved = localStorage.getItem('scrollPosition');
                      if ( saved !== null ) {
                        $(window).scrollTop(parseInt(saved, 10));
                      }
                      // 스크롤할 때마다 저장
                      $(window).on('scroll', function(){
                        localStorage.setItem('scrollPosition', $(window).scrollTop());
                      });
                    }

                    // 모든 링크 클릭 전 처리: 마이페이지 외부 이동 시 scrollPosition 제거
                    $('a').on('click', function(){
                      const href = $(this).attr('href') || '';
                      if ( href.indexOf('/mypage/activity') === -1 ) {
                        localStorage.removeItem('scrollPosition');
                      }
                    });


                    // ====== 11) 배송 조회 모달 (주문 목록/상세 페이지) ======
                    // ol-btn-primary 클래스 중 텍스트에 '배송 조회'가 들어간 버튼 클릭
                    $(document).on('click', '.ol-btn-primary', function(e){
                      if ( $(this).text().indexOf('배송 조회') === -1 ) return;
                      e.preventDefault();

                      // 주문 박스에서 상세 링크 찾기
                      let href;
                      const $orderBox = $(this).closest('.order-box');
                      if ($orderBox.length > 0) {
                        href = $orderBox.find('.order-detail-link').attr('href');
                      }
                      if (!href) {
                        const segs = location.pathname.split('/');
                        href = '/mypage/order-detail/' + segs[segs.length - 1];
                      }
                      const ordId = href.substring(href.lastIndexOf('/') + 1);

                      // AJAX 로 상세 HTML 가져와서 모달에 렌더
                      $.get('/mypage/order-detail/' + ordId)
                        .done(function(response){
                          const $tmp = $('<div>').html(response);
                          const $detail = $tmp.find('.order-detail-section');

                          function getVal(label){
                            return $detail.find('.order-detail-row').filter(function(){
                              return $(this).find('.order-detail-label').text().trim() === label;
                            }).find('.order-detail-value').text().trim();
                          }

                          const productName    = $detail.find('.ol-product-name').text().trim();
                          const productImg     = $detail.find('.ol-product-img').attr('src');
                          const address        = getVal('주소');
                          const receiver       = getVal('받는 사람');
                          const company        = getVal('배송 업체');
                          const trackNum       = getVal('운송장번호');
                          const shippingStart  = $detail.find('.shipping-status-text').data('shipping-start');
                          const shippingArrival= $detail.find('.shipping-status-text').data('shipping-arrival');

                          function formatDate(str){
                            if (!str || str.length<10) return '';
                            const d = new Date(str);
                            return (d.getMonth()+1).toString().padStart(2,'0') + '/' +
                                   d.getDate().toString().padStart(2,'0') + ' ' +
                                   d.getHours().toString().padStart(2,'0') + ':' +
                                   d.getMinutes().toString().padStart(2,'0');
                          }

                          const subtitle = shippingArrival
                            ? shippingArrival.slice(5,10).replace('-','/') + ' 상품이 도착했어요.'
                            : '상품이 배송 중이에요.';

                          const modalHtml = `
                            <div class="shipping-modal-header">
                              <div class="modal-title-bar">
                                <div class="modal-title">배송 상태</div>
                                <button class="modal-close">&times;</button>
                              </div>
                              <div class="modal-subtitle">${subtitle}</div>
                              <div class="shipping-product">
                                <img src="${productImg}" alt="상품 이미지">
                                <span class="shipping-modal-product-name">${productName}</span>
                              </div>
                            </div>
                            <div class="shipping-tracking-info">
                              <div class="shipping-flow-group">
                                ${shippingStart ? `<div class="shipping-step">
                                  <strong>배송 시작</strong>
                                  <small>${formatDate(shippingStart)}</small>
                                </div>` : ''}
                                <div class="shipping-step"><strong>배송 중</strong></div>
                                ${shippingArrival ? `<div class="shipping-step">
                                  <strong>배송 완료</strong>
                                  <small>${formatDate(shippingArrival)}</small>
                                </div>` : ''}
                              </div>
                              <div class="shipping-info-group">
                                <div class="shipping-step">
                                  <small>${address} / ${receiver}</small>
                                </div>
                                <div class="shipping-step">
                                  <small>${company} ${trackNum}</small>
                                </div>
                              </div>
                            </div>
                          `;
                          $('#shippingModalContent').html(modalHtml);
                          $('#shippingModal').fadeIn();
                        })
                        .fail(function(){
                          alert('배송 정보 불러오기 실패');
                        });
                    });
                    $(function() {
                      if (location.pathname.startsWith('/mypage/order-detail')) {
                        $('.ol-btn-primary').each(function(){
                          if ($(this).text().indexOf('배송 조회') !== -1) {
                            $(this).hide();
                          }
                        });
                      }
                    });

                    // 모달 닫기
                    $(document).on('click', '.modal-close', function(){
                      $('#shippingModal').fadeOut();
                    });
                    
                 // 12-1) 상품 상세 페이지 (/product/view/{id})
                   if ($('#product-detail').length > 0) {
                     const $pd       = $('#product-detail');
                     const prdId     = Number($pd.data('prdid'));
                     const mbrId     = $pd.data('mbrid');
                     const ctx       = $pd.data('contextPath');
                     const perPage   = 5;
                     let curPage     = 1;
                     let totalPages  = 1;

                     // 수량 조절
                     $('#increaseQty').click(()=> {
                       const q = parseInt($('#quantity').val(),10);
                       $('#quantity').val(q+1);
                     });
                     $('#decreaseQty').click(()=> {
                       const q = parseInt($('#quantity').val(),10);
                       if (q>1) $('#quantity').val(q-1);
                     });

                     // 장바구니 담기
                     $('#addToCartBtn').click(()=>{
                       const qty = parseInt($('#quantity').val(),10);
                       if (isNaN(qty)||qty<1) return alert('수량을 정확히 입력해주세요!');
                       $.get(ctx+'/member/check-login').done(res=>{
                         if (!res.loggedIn) return $('#loginModal').show();
                         $.ajax({
                           url: ctx+'/cart/add',
                           method:'POST',
                           contentType:'application/json',
                           data: JSON.stringify({ prdId, qty })
                         }).done(()=>{
                           alert('장바구니에 추가되었습니다!');
                           location.href = ctx+'/cart';
                         }).fail(xhr=>{
                           alert('장바구니 추가 실패');
                           console.error(xhr.responseText);
                         });
                       }).fail(()=>alert('서버와 통신 중 오류 발생'));
                     });

                     // 경쟁구매
                     $('#buyroomBtn').click(()=>{
                       $.get(ctx+'/member/check-login').done(res=>{
                         if (!res.loggedIn) return $('#loginModal').show();
                         location.href = ctx+'/room/new?productId='+prdId;
                       }).fail(()=>alert('서버와 통신 중 오류 발생'));
                     });

                     function loadCount(){
                           $.get(`${ctx}/review/product/${prdId}/count`)
                            .done(cnt=> {
                              totalPages = Math.ceil(cnt / perPage);
                              renderPg();
                            });
                         }
                         function loadReviews(page){
                           $.get(`${ctx}/review/product/${prdId}?page=${page}&size=${perPage}`)
                            .done(renderReviews);
                         }
                         function renderReviews(arr){
                           const html = arr.map(r=>`
							<div class="review-item">
							  <h4>${r.rvwTtl}</h4>
							  <p class="rating">
							    <span class="rating-score">${r.rtng}.0</span>
							    <span class="rating-text">점 / 5점</span>
							    <span class="rating-stars">
							    </span>
							  </p>
							  <p>${r.rvwCntnt}</p>
							  <p><small>작성일: ${r.rvwDt}</small></p>
							</div>
                           `).join('');
                           $('#review-list').html(html);
                         }
                         function renderPg(){
                           let html = '';
                           for(let i=1;i<=totalPages;i++){
                             html += `<a href="#" class="page-btn${i===curPage?' active':''}" data-page="${i}">${i}</a>`;
                           }
                           $('#pagination').html(html);
                         }

                         // --- 3) 리뷰 페이징 이벤트 바인딩 ---
                         $(document).on('click', '.page-btn', function(e){
                           e.preventDefault();
                           curPage = Number($(this).data('page'));
                           loadReviews(curPage);
                           renderPg();
                         });

                         // --- 4) 리뷰 작성 핸들러 바인딩 ---
                         $('#reviewSubmitBtn').off('click').on('click', function(){
                           // 로그인 체크
                           $.get(ctx + '/member/check-login')
                            .done(resp=>{
                              if(!resp.loggedIn){
                                $('#loginModal').show();
                                return;
                              }
                              const data = {
                                prdId: prdId,
                                rvwTtl: $('#reviewTitle').val().trim(),
                                rtng: Number($('#rating').val()),
                                rvwCntnt: $('#reviewContent').val().trim()
                              };
                              if(!data.rvwTtl || !data.rvwCntnt || !data.rtng){
                                return alert('제목, 내용, 별점을 모두 입력하세요!');
                              }
                              $.ajax({
                                url: ctx + '/review/create',
                                method: 'POST',
                                contentType: 'application/json',
                                data: JSON.stringify(data)
                              })
                              .done(()=>{
                                alert('리뷰 등록 성공!');
                                // 등록 직후, 페이징 카운트 재조회 + 리뷰 목록 재로드
                                loadCount();
                                loadReviews(curPage);
                                // 입력창 초기화
                                $('#reviewTitle').val('');
                                $('#reviewContent').val('');
                                $('#rating').val('');
                              })
                              .fail(()=>{
                                alert('리뷰 등록 실패');
                              });
                            })
                            .fail(()=>{
                              alert('서버와 통신 중 오류 발생');
                            });
                         });


                     // 로그인 모달 닫기 & 폼 처리
                     $(document).on('click','#closeLoginModal',()=>$('#loginModal').hide());
                     $(document).on('submit','#loginForm',function(e){
                       e.preventDefault();
                       const data = {
                         mbrInsId: $('#loginForm input[name="mbrInsId"]').val(),
                         mbrPwd:   $('#loginForm input[name="mbrPwd"]').val()
                       };
                       $.ajax({
                         url: ctx+'/member/ajax-login',
                         method:'POST',
                         contentType:'application/json',
                         data: JSON.stringify(data)
                       }).done(res=>{
                         if(res.loggedIn){ $('#loginModal').hide(); location.reload(); }
                         else $('#login-error').text('아이디 또는 비밀번호를 확인하세요.').show();
                       }).fail(()=>$('#login-error').text('서버 오류 발생').show());
                     });

                     loadCount();
                     loadReviews(curPage);
                   }

                   // 12-2) 상품 리스트 페이지 (/product/list)
                   if ($('.product-list-page').length > 0) {
                     // 카드 클릭 이동
                     $('.product-card-link').on('click', function(e){
                       // a 태그 기본 동작으로 충분
                     });
                     // 관리자 상품 삭제 (이벤트 위임)
                     $(document).on('click','.delete-btn',function(){
                       const prdId = $(this).data('prdid');
                       const btn   = $(this);
                       if (!confirm('정말 삭제하시겠습니까?')) return;
                       $.post('/product/delete/'+prdId).done(()=>{
                         alert('상품이 삭제되었습니다.');
                         btn.closest('.product-card').remove();
                         if ($('.product-card').length===0) location.reload();
                       }).fail(xhr=> {
                         alert('상품 삭제 실패: '+xhr.responseText);
                       });
                     });
                   }

                   // 12-3) 상품 등록 페이지 (/product/register)
                   if ($('.product-register-page').length > 0) {
                   }
                   // ====== 13) 룰렛 돌리기 페이지 전용 로직 ======
                     if ( $('.roulette-spin-page').length && Array.isArray(segments) ) {
                       const $wheel      = $('#rouletteWheel');
                       const $btn        = $('.spin-button');
                       const $modal      = $('#spinModal');
                       const $close      = $('#closeSpinModal');
                       const segCount    = segments.length;
                       const segAngle    = 360 / segCount;
                       const ctx         = window.contextPath || '';

                       // 1) Wheel 위에 라벨 배치
                       segments.forEach((seg,i) => {
                         const revIdx    = segCount - 1 - i;
                         const baseA     = segAngle * revIdx + segAngle/2;
                         $('<div>')
                           .addClass('segment-label')
                           .text(seg.name)
                           .css('transform',
                             `rotate(${baseA}deg)
                              translate(0, -110px)
                              rotate(${-baseA}deg)`
                           )
                           .appendTo($wheel);
                       });

                       // 2) Spin 버튼
                       $btn.on('click', function(){
                         const bonus = parseInt($('#bonusCountDisplay').text(), 10);
                         if ( bonus <= 0 ) {
                           return alert('보너스 횟수가 부족하여 룰렛을 돌릴 수 없습니다.');
                         }
                         $.ajax({
                           url: ctx + '/roulette/spin',
                           method: 'POST',
                           dataType: 'json'
                         }).done(data => {
                           if ( data.status === 'success' ) {
                             $('#bonusCountDisplay').text(data.bnftCnt);
                             const idx = segments.findIndex(s => s.id === data.selectedBenefitId);
                             const finalA = 360*5 + idx*segAngle + segAngle/2;
                             $wheel.css({
                               transition: 'transform 5s cubic-bezier(0.33,1,0.68,1)',
                               transform: `rotate(${finalA}deg)`
                             }).one('transitionend', () => {
                               $('#spinMessage').html(`
                                 <h2>당첨 결과</h2>
                                 <p>혜택: ${segments[idx].name}</p>
                               `);
                               if ( segments[idx].name === '추가 룰렛 찬스' ) {
                                 const cur = parseInt($('#bonusCountDisplay').text(),10);
                                 $('#bonusCountDisplay').text(cur+1);
                                 $.post(ctx + '/roulette/addChance');
                               }
                               $modal.addClass('show');
                             });
                           } else {
                             alert(data.message);
                           }
                         }).fail((_,status) => {
                           if ( confirm(`룰렛 돌리기 요청 실패: ${status}\n로그인하시겠습니까?`) ) {
                             const curUrl = location.pathname + location.search;
                             location.href = ctx + '/member/login?redirect=' + encodeURIComponent(curUrl);
                           }
                         });
                         $btn.prop('disabled', true);
                       });

                       // 3) 모달 닫기 → 리셋
                       $close.on('click', () => {
                         $modal.removeClass('show');
                         $btn.prop('disabled', false);
                         $wheel.css({ transition: 'none', transform: 'rotate(0deg)' });
                         setTimeout(()=> {
                           $wheel.css('transition','transform 5s cubic-bezier(0.33,1,0.68,1)');
                         }, 20);
                       });
                     }
                     // ====== 14) 패키지 리스트 페이지 전용 로직 ======
                       if ( $('.package-list-page').length > 0 ) {
                        // URL 의 activeTab 파라미터 읽기 (없으면 userTab)
                            function getActiveTabFromUrl() {
                              const params = new URLSearchParams(window.location.search);
                              const t = params.get('activeTab');
                              return (t === 'adminTab' ? 'adminTab' : 'userTab');
                            }

                            // 탭 전환 및 URL 업데이트
                            window.showTab = function(id) {
                              $('#userTab, #adminTab').hide();
                              $('#' + id).show();
                              // URL 파라미터에 반영 (뒤로가기도 덜 꼬이게 replaceState 사용)
                              const url = new URL(window.location);
                              url.searchParams.set('activeTab', id);
                              window.history.replaceState({}, '', url);
                            };

                         // 2) 전체선택 토글
                         window.toggleAll = function(src) {
                           $('.packageCheckbox').prop('checked', src.checked);
                         };

                         // 3) 선택된 패키지 장바구니 담기
                         window.submitCart = function() {
                           const ids = $('.packageCheckbox:checked').map(function(){ return this.value; }).get();
                           if (!ids.length) {
                             return alert('패키지를 선택해주세요.');
                           }
                           fetch('/cart/addPackages', {
                             method: 'POST',
                             headers: {'Content-Type':'application/json'},
                             body: JSON.stringify({ packageIds: ids })
                           })
                           .then(r => r.text())
                           .then(res => {
                             if (res === 'success') {
                               alert('장바구니에 담겼습니다.');
                               location.href = '/cart';
                             }
                             else if (res === 'not_logged_in') {
                               alert('로그인이 필요합니다.');
                               location.href = '/member/login';
                             }
                             else {
                               alert('장바구니 담기에 실패했습니다.');
                             }
                           })
                           .catch(() => alert('서버 오류가 발생했습니다.'));
                         };

                         // 4) 추천 버튼 AJAX
                         function attachRecommendEvents(){
                           $('.recommend-btn').each(function(){
                             const btn = $(this);
                             btn.off('click').on('click', function(){
                               fetch('/shoplaylist/recommend/' + btn.data('id'), { method: 'POST' })
                                 .then(r => r.ok ? r.json() : r.text().then(txt=>{throw txt;}))
                                 .then(cnt => {
                                   $('#rcount-' + btn.data('id')).text(cnt);
                                   btn.prop('disabled', true);
                                 })
                                 .catch(e => alert(e || '추천 실패'));
                             });
                           });
                         }

                         // 5) 초기화
                         showTab( getActiveTabFromUrl() );
                         attachRecommendEvents();

                         // bfcache 대응
                         window.addEventListener('pageshow', function(e){
                           if (e.persisted) {
                             showTab( getActiveTabFromUrl() );
                           }
                         });
                       }
    });

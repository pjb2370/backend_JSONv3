package com.investmentsite.isc.user.service;

import com.investmentsite.isc.user.dao.SluserRepository;
import com.investmentsite.isc.user.domain.Sluser;
import com.investmentsite.isc.user.dto.SluserInput;
import com.investmentsite.isc.user.dto.SluserSighInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SluserService {
    private final SluserRepository sluserRepository;

    //회원가입
    public boolean signUp(SluserInput sluserInput){
        if (sluserInput.getPassword().equals(sluserInput.getConfirmPassword())){
            Sluser S1 = new Sluser();
            S1.setUserId(sluserInput.getUserId());
            S1.setUserName(sluserInput.getUserName());
            S1.setPassword(sluserInput.getPassword());
            this.sluserRepository.save(S1);
            return true;
        }else {
            return false;
        }
    }

    //로그인
    public SluserSighInDto sighIn(SluserInput sluserInput){
        Optional<Sluser> opSluser = Optional.ofNullable(sluserRepository.findByUserId(sluserInput.getUserId()));
        SluserSighInDto sluserSighInDto = new SluserSighInDto();
        if (opSluser.isPresent()){
            Sluser sluser = sluserRepository.findByUserId(sluserInput.getUserId());
            if (sluser.getPassword().equals(sluserInput.getPassword())){
                //로그인에 성공했을때만, 유저네임과 true 반환
                //실패했을땐 null과 false반환
                sluserSighInDto.setUserName(opSluser.get().getUserName());
                sluserSighInDto.setABoolean(true);
                System.out.println(sluserSighInDto);
                return sluserSighInDto;
            }else {
                sluserSighInDto.setUserName(null);
                sluserSighInDto.setABoolean(false);
                return sluserSighInDto;
            }
        }else {
            sluserSighInDto.setUserName(null);
            sluserSighInDto.setABoolean(false);
            return sluserSighInDto;
        }
    }

    //패스워드 재설정
    public boolean pwReset(SluserInput sluserInput){
        //입력받은 패스워드와 체크패스워드가 같을때
        if (sluserInput.getPassword().equals(sluserInput.getConfirmPassword())){
            Optional<Sluser> opSluserrs = Optional.ofNullable(this.sluserRepository.findByUserId(sluserInput.getUserId()));
            SluserSighInDto sluserSighInDto = new SluserSighInDto();
            //찾은 id가 db에 존재할때
            if (opSluserrs.isPresent()){
                Sluser sluser = this.sluserRepository.findByUserId(sluserInput.getUserId());
                sluser.setPassword(sluserInput.getPassword());
                this.sluserRepository.save(sluser);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public Optional sluserGet(SluserInput sluserInput){
        Optional<Sluser> opSluser = Optional.ofNullable(sluserRepository.findByUserId(sluserInput.getUserId()));
        return opSluser;
    }
}

package com.example.studentscompanion;

public class Register_bp_members
{
        String memeberID;
        private String mEname;
        private Long Admiss;
        private String Branch;
        private Long Contact;
        private String subs;

        public Register_bp_members() {
        }

    public Register_bp_members(String memeberID, String mEname,
                               Long admiss, String branch, Long contact, String subs) {
        this.memeberID = memeberID;
        this.mEname = mEname;
        Admiss = admiss;
        Branch = branch;
        Contact = contact;
        this.subs = subs;
    }

    public String getMemeberID() {
        return memeberID;
    }

    public String getmEname() {
        return mEname;
    }

    public Long getAdmiss() {
        return Admiss;
    }

    public String getBranch() {
        return Branch;
    }

    public Long getContact() {
        return Contact;
    }

    public String getSubs() {
        return subs;
    }
}
